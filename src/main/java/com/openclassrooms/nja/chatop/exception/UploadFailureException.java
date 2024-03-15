package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class UploadFailureException extends BaseException {
    public UploadFailureException(String message) {
        super(HttpStatus.BAD_REQUEST, "UPLOAD_FAILED", Collections.singletonList(message));
    }
}
