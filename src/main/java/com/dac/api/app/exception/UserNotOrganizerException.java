package com.dac.api.app.exception;

public class UserNotOrganizerException extends RuntimeException {
    public UserNotOrganizerException() {
        super("Usuário não é o organizador da edição.");
    }
}
