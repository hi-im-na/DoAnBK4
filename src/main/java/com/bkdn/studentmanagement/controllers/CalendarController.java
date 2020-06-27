package com.bkdn.studentmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    String text = "10";

    @GetMapping("/calendar")
    public String calendar (Model m){
        m.addAttribute("message", "Hello");
        return "layouts/admin/pages/calendar";
    }
}