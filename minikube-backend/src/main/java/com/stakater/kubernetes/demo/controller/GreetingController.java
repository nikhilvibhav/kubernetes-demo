package com.stakater.kubernetes.demo.controller;

import com.stakater.kubernetes.demo.exception.BlankEnvironmentVariableValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller that exposes the API to Get a greeting
 *
 * @author Nikhil Vibhav
 */
@RestController
@RequestMapping(path = "/api/v1/kubernetes-demo", produces = MediaType.TEXT_PLAIN_VALUE)
public class GreetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    @Value("${NAME}")
    private String name;

    /**
     * Greet endpoint returns "Hello + ${NAME}"
     *
     * @return "Hello + ${NAME}"
     * @throws BlankEnvironmentVariableValueException when the environment variable has a blank/null value
     */
    @CrossOrigin
    @GetMapping("/greet")
    public ResponseEntity<String> greet() throws BlankEnvironmentVariableValueException {

        if (name == null || name.isBlank()) {
            throw new BlankEnvironmentVariableValueException(
                    "The value provided for the environment variable 'NAME' is either blank or null");
        }

        LOGGER.info("Environment variable NAME - {}", name);

        return ResponseEntity.ok("Hello " + name);
    }

}
