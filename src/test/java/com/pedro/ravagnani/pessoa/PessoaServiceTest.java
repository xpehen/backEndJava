package com.pedro.ravagnani.pessoa;

import com.pedro.ravagnani.contato.domain.Contato;
import com.pedro.ravagnani.pessoa.domain.Pessoa;
import com.pedro.ravagnani.pessoa.domain.PessoaRepository;
import com.pedro.ravagnani.pessoa.domain.PessoaService;
import com.pedro.ravagnani.util.IdGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@RunWith(SpringRunner.class)
public class PessoaServiceTest {

    @MockBean
    private PessoaRepository pessoaRepository;

    @SpyBean
    private PessoaService pessoaService;

    private String idGerado;
    private Contato contato;
    private Pessoa pessoa;
    private Page<Pessoa> pessoaPage;


    @Before
    public void inicializar(){
        List<Contato> listContatos = new ArrayList<>();
        List<Pessoa> listPessoas = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.set(1997, Calendar.MARCH, 02);
        Date nascimento = c.getTime();

        contato = Contato.builder()
                .id(IdGenerator.generate())
                .nome("Pedro Ravagnani")
                .email("pedro.ravaganani@email.com")
                .telefone(1234567890L)
                .build();

        pessoa = Pessoa.builder()
                .id(IdGenerator.generate())
                .nome("Pedro Ravagnani")
                .cpf("44996124890")
                .dataNascimento(nascimento)
                .contatos(listContatos)
                .build();

        listPessoas.add(pessoa);
        listContatos.add(contato);

        pessoaPage = new PageImpl<Pessoa>(listPessoas);
    }

    @Test
    public void findById() {
        Mockito.when(pessoaRepository.findById(Mockito.any())).thenReturn(Optional.of(pessoa));
        Assert.assertEquals(pessoa,pessoaService.findById(pessoa.getId()));
    }

    @Test
    public void insert() {
        Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(pessoa);
        Assert.assertEquals(pessoa, pessoaService.insert(pessoa));
    }

    @Test
    public void update() {
        Mockito.when(pessoaRepository.findById(Mockito.any())).thenReturn(Optional.of(pessoa));
        Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(pessoa);

        Assert.assertEquals(pessoa, pessoaService.update(pessoa));
    }

    @Test
    public void delete() {
        pessoaService.delete(pessoa);
    }

    @Test
    public void findAllFilterNull() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(pessoaRepository.findAll(pageable)).thenReturn(pessoaPage);

        Assert.assertEquals(pessoaPage, pessoaService.findAll(null,0,10));
    }

    @Test
    public void findAllFilter() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(pessoaRepository.findAllByNome(pageable, "Pedro Ravagnani")).thenReturn(pessoaPage);

        Assert.assertEquals(pessoaPage, pessoaService.findAll("Pedro Ravagnani",0,10));
    }
}