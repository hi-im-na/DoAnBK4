package com.bkdn.studentmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Err403Controller {

    @GetMapping("/403")
    public String error() {
        return "error/403";
    }
}