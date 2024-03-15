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

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResponseDTO> handleBaseException(BaseException ex, WebRequest request) {
        logger.error("Error occurred: {}", ex.getMessage(), ex);
        ResponseDTO response = ResponseDTO.builder()
                .status(ex.getStatus())
                .message(ex.getMessage())
                .errors(ex.getErrors())
                .build();
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleException(Exception ex, WebRequest request) {
        logger.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        ResponseDTO response = ResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An unexpected error occurred.")
                .errors(Collections.singletonList(ex.getMessage()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
