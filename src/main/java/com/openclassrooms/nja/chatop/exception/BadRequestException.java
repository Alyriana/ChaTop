package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

/**
 * Represents an exception that is thrown when a bad request is made.
 */
public class BadRequestException extends BaseException {
    // Constructor initializes the exception with a specific bad request status,
    // an "BAD_REQUEST" error code, and a detailed error message.
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, "BAD_REQUEST", Collections.singletonList(message));
    }
}
