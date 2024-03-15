package com.openclassrooms.nja.chatop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class BaseException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final List<String> errors;

    protected BaseException(HttpStatus status, String code, List<String> errors) {
        super(errors != null && !errors.isEmpty() ? errors.get(0) : null);
        this.status = status;
        this.code = code;
        this.errors = errors;
    }
}
