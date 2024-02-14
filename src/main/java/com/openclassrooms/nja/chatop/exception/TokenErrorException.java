package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TokenErrorException extends RuntimeException {
    public TokenErrorException(String message) {
        super(message);
    }
}
