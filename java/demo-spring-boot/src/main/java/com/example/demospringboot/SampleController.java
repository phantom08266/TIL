package com.example.demospringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return "hello " + name;
    }
    @GetMapping("/hi/{name}")
    public String hi(@PathVariable("name") Person person){
        return "hi "+ person.getName();
    }
    @GetMapping("/check")
    public String check(@RequestParam("test") Person person){
        return "check " + person.getName();
    }
}
