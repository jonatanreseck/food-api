package com.algafoodapi.api.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

    private static final long serialVerionUID = 1L;

    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }
    
}
