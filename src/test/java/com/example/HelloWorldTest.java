package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class HelloWorldTest {
    @Test
    void mainRuns() {
        assertDoesNotThrow(() -> HelloWorld.main(new String[]{}));
    }
}
