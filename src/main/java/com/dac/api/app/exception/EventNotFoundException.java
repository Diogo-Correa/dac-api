package com.dac.api.app.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Evento não encontrada");
    }
}
