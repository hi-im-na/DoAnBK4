package com.bkdn.studentmanagement.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @PreAuthorize("")
@Controller
public class DashboardController {

    @GetMapping(value = "/dashboard")
    public String dashboard() {
        return "layouts/admin/pages/dashboard";
    }
}
