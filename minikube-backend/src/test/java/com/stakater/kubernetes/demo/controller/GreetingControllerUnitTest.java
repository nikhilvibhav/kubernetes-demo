package com.stakater.kubernetes.demo.controller;

import com.stakater.kubernetes.demo.exception.BlankEnvironmentVariableValueException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link GreetingController}
 *
 * @author Nikhil Vibhav
 */
public class GreetingControllerUnitTest {

    private GreetingController greetingController;

    @BeforeEach
    public void setup() throws Exception {
        greetingController = new GreetingController();
    }

    @Test
    public void givenRequest_WhenGreet_ThenSucceed() throws Exception {
        ReflectionTestUtils.setField(greetingController, "name", "Test Name");

        final ResponseEntity<String> response = assertDoesNotThrow(() -> greetingController.greet());

        assertNotNull(response);
        assertEquals("Hello Test Name", response.getBody());
    }

    @Test
    public void givenRequest_WhenGreet_ThenFail() throws Exception {
        ReflectionTestUtils.setField(greetingController, "name", "");

        final BlankEnvironmentVariableValueException exception =
                assertThrows(BlankEnvironmentVariableValueException.class, () -> greetingController.greet());

        assertNotNull(exception);
        assertEquals("The value provided for the environment variable 'NAME' is either blank or null",
                exception.getMessage());
    }

}
