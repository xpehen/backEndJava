package com.pedro.ravagnani.pessoa.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, String>{
    Page<Pessoa> findAllByNome(Pageable pageable, String nome);
}
