package com.aftas.aftasapi.exceptions;

public class CompetitionNotFoundException extends RuntimeException{
    public CompetitionNotFoundException(String message) {
        super(message);
    }
}
