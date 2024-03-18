package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

/**
 * UserAlreadyExistsException is a custom exception that is thrown when a user with the same email already exists.
 * It extends the BaseException class and provides a specific error code and status for the exception.
 */
public class UserAlreadyExistsException extends BaseException {
    // Constructor initializes the exception with a specific conflict status,
    // an "USER_ALREADY_EXISTS" error code, and a detailed error message.
    public UserAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, "USER_ALREADY_EXISTS", Collections.singletonList(message));
    }
}
