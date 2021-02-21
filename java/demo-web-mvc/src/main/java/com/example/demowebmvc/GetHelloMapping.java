package com.example.demowebmvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value = "/hello", method = RequestMethod.GET)
public @interface GetHelloMapping {
}
