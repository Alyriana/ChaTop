package com.openclassrooms.nja.chatop.exception;

import com.openclassrooms.nja.chatop.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

/**
 *
 */
@ControllerAdvice
// Marks this class as advice that is globally applicable to all controllers, enabling centralized exception handling.
public class GlobalExceptionHandler {
    // Logger for logging error details.
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handles exceptions of type BaseException, custom exceptions defined for the application.
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResponseDTO> handleBaseException(BaseException ex, WebRequest request) {
        // Logs the error details.
        logger.error("Error occurred: {}", ex.getMessage(), ex);
        // Prepares a response object with the exception details.
        ResponseDTO response = ResponseDTO.builder()
                .status(ex.getStatus())
                .message(ex.getMessage())
                .errors(ex.getErrors())
                .build();
        // Returns a response entity with the constructed response object and HTTP status from the exception.
        return new ResponseEntity<>(response, ex.getStatus());
    }

    // Handles all other exceptions that are not explicitly handled by other @ExceptionHandler methods.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleException(Exception ex, WebRequest request) {
        // Logs the error as an unhandled exception.
        logger.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        // Constructs a generic response for unexpected errors.
        ResponseDTO response = ResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An unexpected error occurred.")
                .errors(Collections.singletonList(ex.getMessage()))
                .build();
        // Returns the response with an INTERNAL_SERVER_ERROR status.
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
