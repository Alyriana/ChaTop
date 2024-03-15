package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class TokenErrorException extends BaseException {
    public TokenErrorException(String message) {
        super(HttpStatus.UNAUTHORIZED, "TOKEN_ERROR", Collections.singletonList(message));
    }
}
