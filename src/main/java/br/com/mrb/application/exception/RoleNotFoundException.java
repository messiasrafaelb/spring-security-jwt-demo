package br.com.mrb.application.exception;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(HttpStatus badRequest, String message) {
        super(message);
    }
}
