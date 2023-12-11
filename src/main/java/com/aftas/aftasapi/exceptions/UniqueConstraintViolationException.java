package com.aftas.aftasapi.exceptions;

public class UniqueConstraintViolationException extends RuntimeException{
    public UniqueConstraintViolationException(String message) {
        super(message);
    }
}
