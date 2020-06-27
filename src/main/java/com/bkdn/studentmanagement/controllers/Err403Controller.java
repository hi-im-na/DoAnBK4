package com.bkdn.studentmanagement.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class Err403Controller {

    @GetMapping("/403")
    public String error() {
        return "error/403";
    }
}