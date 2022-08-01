package com.myretail.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping(value = "/", produces = "application/json")
    public String heartBeat() {
        return "{\"status\": \"OK\"}";
    }
}
