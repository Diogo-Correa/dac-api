package com.dac.api.app.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário já existe.");
    }
}
