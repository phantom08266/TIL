package com.example.tobyspring.service;

import org.springframework.stereotype.Component;

@Component
public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
