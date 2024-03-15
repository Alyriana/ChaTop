package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class AuthFailedException extends BaseException {
    public AuthFailedException(String message) {
        super(HttpStatus.UNAUTHORIZED, "AUTH_FAILED", Collections.singletonList(message));
    }
}