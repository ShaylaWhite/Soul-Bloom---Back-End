package com.example.soulbloom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * This exception is thrown when an attempt to create or modify information results in a conflict
 * because the information already exists.
 *
 * @ResponseStatus(HttpStatus.CONFLICT) indicates that when this exception is thrown, it results in an HTTP 409 Conflict response status.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException {

    /**
     * Constructs a new InformationExistException with the specified error message.
     *
     * @param message The error message that describes the conflict or the reason for the exception.
     */
    public InformationExistException(String message) {
        super(message);
    }
}
