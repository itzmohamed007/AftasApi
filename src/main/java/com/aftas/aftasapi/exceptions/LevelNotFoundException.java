package com.aftas.aftasapi.exceptions;

public class LevelNotFoundException extends RuntimeException{
    public LevelNotFoundException(String message) {
        super(message);
    }
}
