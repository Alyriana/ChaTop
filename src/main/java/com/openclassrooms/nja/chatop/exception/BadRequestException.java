package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, "BAD_REQUEST", Collections.singletonList(message));
    }
}
