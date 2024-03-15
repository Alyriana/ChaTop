package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, "USER_ALREADY_EXISTS", Collections.singletonList(message));
    }
}
