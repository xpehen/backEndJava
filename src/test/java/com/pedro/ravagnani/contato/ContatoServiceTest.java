package com.pedro.ravagnani.contato;

import com.pedro.ravagnani.contato.domain.Contato;
import com.pedro.ravagnani.contato.domain.ContatoRepository;
import com.pedro.ravagnani.contato.domain.ContatoService;
import com.pedro.ravagnani.pessoa.domain.Pessoa;
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
public class ContatoServiceTest {

    @MockBean
    private ContatoRepository contatoRepository;

    @SpyBean
    private ContatoService contatoService;

    private Contato contato;
    private Pessoa pessoa;
    private Page<Contato> contatoPagina;


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
                .email("pedro.ravagnani@email.com")
                .telefone(18996619001L)
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

        contatoPagina = new PageImpl<Contato>(listContatos);
    }

    @Test
    public void findById() {
        Mockito.when(contatoRepository.findById(Mockito.any())).thenReturn(Optional.of(contato));

        Assert.assertEquals(contato,contatoService.findById(contato.getId()));
    }

    @Test
    public void insert() {
        Mockito.when(contatoRepository.save(Mockito.any())).thenReturn(contato);

        Assert.assertEquals(contato, contatoService.insert(contato));
    }

    @Test
    public void update() {
        Mockito.when(contatoRepository.findById(Mockito.any())).thenReturn(Optional.of(contato));
        Mockito.when(contatoRepository.save(Mockito.any())).thenReturn(contato);

        Assert.assertEquals(contato, contatoService.update(contato));
    }

    @Test
    public void delete() {
        contatoService.delete(contato);
    }

    @Test
    public void findAllFilterNull() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(contatoRepository.findAll(pageable)).thenReturn(contatoPagina);

        Assert.assertEquals(contatoPagina, contatoService.findAll(null,0,10));
    }

    @Test
    public void findAllFilter() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(contatoRepository.findAllByNome(pageable, "Pedro Ravagnani")).thenReturn(contatoPagina);

        Assert.assertEquals(contatoPagina, contatoService.findAll("Pedro Ravagnani",0,10));
    }
}