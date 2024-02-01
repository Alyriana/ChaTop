package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreationFailureException extends RuntimeException {
    public CreationFailureException(String message) {
        super(message);
    }

}