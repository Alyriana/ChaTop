package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", Collections.singletonList(message));
    }
}
