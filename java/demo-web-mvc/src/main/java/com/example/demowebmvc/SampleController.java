package com.example.demowebmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {

    @GetHelloMapping
    @ResponseBody
    public String hello(){
        return "hello";
    }


}
