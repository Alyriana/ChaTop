package com.openclassrooms.nja.chatop.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

/**
 * UploadFailureException is a custom exception that is thrown when an upload fails.
 * It extends the BaseException class and provides a constructor to set the HTTP status code,
 * an error code, and a list of error messages associated with the exception.
 */
public class UploadFailureException extends BaseException {
    // Constructor initializes the exception with a specific bad request status,
    // an "UPLOAD_FAILED" error code, and a detailed error message.
    public UploadFailureException(String message) {
        super(HttpStatus.BAD_REQUEST, "UPLOAD_FAILED", Collections.singletonList(message));
    }
}
