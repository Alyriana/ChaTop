package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class CreationFailureException extends BaseException {
    public CreationFailureException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "CREATION_FAILED", Collections.singletonList(message));
    }
}
