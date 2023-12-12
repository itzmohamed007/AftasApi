package com.aftas.aftasapi.exceptions;

public class DuplicatedCodeException extends RuntimeException{
    public DuplicatedCodeException(String message) {
        super(message);
    }
}
