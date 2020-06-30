package com.bkdn.studentmanagement.controllers;

import com.bkdn.studentmanagement.models.PlanModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreatePlanController {

    @GetMapping("/newplan")
    public String newPlanGet() {
        return "layouts/admin/pages/newplan";
    }

    @PostMapping("/newplan")
    public String newPlanPost(@RequestParam("title") String title, @RequestParam("time") String time,
            @RequestParam("date") String date, @RequestParam("location") String location) {
        // PlanModel pModel = new PlanModel();
        System.out.println(date);
        System.out.println(time);
        System.out.println(location);
        return "redirect:/calendar";
    }

}