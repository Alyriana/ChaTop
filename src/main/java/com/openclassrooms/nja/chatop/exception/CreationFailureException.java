package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CreationFailureException extends RuntimeException {

    public CreationFailureException(String message) {
        super(message);
    }

    public CreationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
