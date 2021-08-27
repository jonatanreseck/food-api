package com.algafoodapi.api.exception;

public class EntidadeEmUsoException extends RuntimeException {

    private static final long serialVerionUID = 1L;

    public EntidadeEmUsoException(String mensagem){
        super(mensagem);
    }
    
}
