package br.com.mrb.application.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(HttpStatus badRequest, String message) {
        super(message);
    }
}
