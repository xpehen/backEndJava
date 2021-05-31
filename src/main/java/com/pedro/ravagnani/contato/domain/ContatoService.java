package com.pedro.ravagnani.contato.domain;

import com.pedro.ravagnani.contato.exception.ContatoNotFoundException;
import com.pedro.ravagnani.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public Contato findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ContatoNotFoundException(id));
    }

    public Contato insert(Contato contato) {
        contato.setId(IdGenerator.generate());
        return repository.save(contato);
    }

    public Contato update(Contato contatoAtualizado) {

        return repository.findById(contatoAtualizado.getId())
                .map(contato -> {
                    contato.setNome(contatoAtualizado.getNome());
                    contato.setTelefone(contatoAtualizado.getTelefone());
                    contato.setEmail(contatoAtualizado.getEmail());
                    return repository.save(contato);
                })
                .orElseThrow(() -> new ContatoNotFoundException(contatoAtualizado.getId()));
    }

    public void delete(Contato contatoDeletado) {
        repository.delete(contatoDeletado);
    }

    public Page<Contato> findAll(String filter, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        if(!StringUtils.hasLength(filter)){
            return repository.findAll(pageable);
        }else{
            return repository.findAllByNome(pageable, filter);
        }

    }
}
