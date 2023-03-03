package com.example.tobyspring.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleHelloServiceTest {

    @Test
    void simpleHelloServiceTest() {
        SimpleHelloService service = new SimpleHelloService();
        String result = service.sayHello("test");

        assertThat(result).isEqualTo("hello test");
    }

    @Test
    void decoratorServiceTest() {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String result = decorator.sayHello("test");

        assertThat(result).isEqualTo("*test*");
    }
}