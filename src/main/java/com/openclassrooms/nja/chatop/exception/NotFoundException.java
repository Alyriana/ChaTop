package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

/**
 * NotFoundException is a custom exception class that is thrown when a resource is not found.
 * It extends the BaseException class and provides a constructor to initialize the exception
 * with an error message.
 */
public class NotFoundException extends BaseException {
    // Constructor initializes the exception with a specific not found status,
    // an "NOT_FOUND" error code, and a detailed error message.
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, "NOT_FOUND", Collections.singletonList(message));
    }
}
