package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, "NOT_FOUND", Collections.singletonList(message));
    }
}
