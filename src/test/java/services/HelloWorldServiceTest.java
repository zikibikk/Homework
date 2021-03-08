package services;

import org.junit.jupiter.api.*;


class HelloWorldServiceTest {

    @Test
    void getGreeting() {
        new HelloWorldService().getGreeting();
    }
}