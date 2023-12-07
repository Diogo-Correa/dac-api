package com.dac.api.app.exception;

public class SpaceNotFoundException extends RuntimeException {
    public SpaceNotFoundException() {
        super("Espaço não encontrado.");
    }
}
