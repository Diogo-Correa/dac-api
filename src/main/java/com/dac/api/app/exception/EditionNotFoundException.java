package com.dac.api.app.exception;

public class EditionNotFoundException extends RuntimeException {
    public EditionNotFoundException() {
        super("Edição não encontrada");
    }
}
