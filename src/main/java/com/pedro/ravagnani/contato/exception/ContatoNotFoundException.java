package com.pedro.ravagnani.contato.exception;

public class ContatoNotFoundException extends RuntimeException{

    public ContatoNotFoundException(String id) {
        super("O contato informado não foi encontrado.");
    }

}