package com.stakater.kubernetes.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when the environment variable has a blank/null value
 *
 * @author Nikhil Vibhav
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,
        reason = "The required environment variable had a blank or null value")
public class BlankEnvironmentVariableValueException extends Exception {

    public BlankEnvironmentVariableValueException(String message) {
        super(message);
    }
}
