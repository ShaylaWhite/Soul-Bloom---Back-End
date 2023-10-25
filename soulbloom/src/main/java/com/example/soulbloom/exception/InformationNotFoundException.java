package com.example.soulbloom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when an attempt to retrieve information results in a not found scenario.
 *
 * @ResponseStatus(HttpStatus.NOT_FOUND) indicates that when this exception is thrown, it results in an HTTP 404 Not Found response status.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {

    /**
     * Constructs a new InformationNotFoundException with the specified error message.
     *
     * @param message The error message that describes the not found scenario or the reason for the exception.
     */
    public InformationNotFoundException(String message) {
        super(message);
    }
}