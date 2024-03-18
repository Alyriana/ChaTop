package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

/**
 * UnauthorizedException is a custom exception class that is thrown when an unauthorized access is detected.
 * It extends the BaseException class and provides a constructor to initialize the exception
 * with an error message.
 */
public class UnauthorizedException extends BaseException {
    // Constructor initializes the exception with a specific unauthorized status,
    // an "UNAUTHORIZED" error code, and a detailed error message.
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", Collections.singletonList(message));
    }
}
