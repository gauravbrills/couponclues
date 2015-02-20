package com.sapient.couponclues.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class Example {

    /*
     * public static void main(String[] args) throws Exception { SpringApplication.run(Example.class, args); }
     */

    @RequestMapping("/test")
    String home() {

        return "Hello World!";
    }

}