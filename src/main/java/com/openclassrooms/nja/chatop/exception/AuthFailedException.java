package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AuthFailedException extends RuntimeException {
    public AuthFailedException(String message, BadCredentialsException e) {
        super(message);
    }
}
