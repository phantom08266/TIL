package com.example.demowebmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @GetMapping("/events")
//    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/events/{id}")
//    @ResponseBody
    public String helloNumber(@PathVariable int id) {
        return "helloNumber " + id;
    }

    @PostMapping(
            value = "/events",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String helloPost() {
        return "helloPost";
    }

    @DeleteMapping(value = "/events/{id}")
    public String helloDelete(@PathVariable int id){
        return "helloDelete " + id;
    }

    @PutMapping(
            value = "/events/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String helloPut(@PathVariable int id){
        return "helloPut " + id;
    }
}
