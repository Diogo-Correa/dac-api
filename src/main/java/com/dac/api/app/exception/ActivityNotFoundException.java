package com.dac.api.app.exception;

public class ActivityNotFoundException extends RuntimeException {
    public ActivityNotFoundException() {
        super("Atividade não encontrada.");
    }
}
