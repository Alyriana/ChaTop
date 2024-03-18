package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

/**
 * Exception class for creation failure.
 */
public class CreationFailureException extends BaseException {
    // Constructor initializes the exception with a specific internal server error status,
    // an "CREATION_FAILED" error code, and a detailed error message.
    public CreationFailureException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "CREATION_FAILED", Collections.singletonList(message));
    }
}
