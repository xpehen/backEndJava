package com.pedro.ravagnani.pessoa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PessoaNotFoundException extends RuntimeException{

    public PessoaNotFoundException(String id) {
        super("A pessoa não foi encontrada");
    }

}