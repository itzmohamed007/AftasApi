package com.aftas.aftasapi.exceptions;

public class FishNotFoundException extends RuntimeException {
    public FishNotFoundException(String message) {
        super(message);
    }
}
