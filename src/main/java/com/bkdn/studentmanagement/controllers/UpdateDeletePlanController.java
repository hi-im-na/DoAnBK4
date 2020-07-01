package com.bkdn.studentmanagement.controllers;

import com.bkdn.studentmanagement.models.PlanModel;
import com.bkdn.studentmanagement.services.PlanInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateDeletePlanController {

    @Autowired
    PlanInfoService planInfoService;

    @GetMapping("/updateplan")
    public String updatePlanGet(Model m, @RequestParam(value = "planModelString") String planModelString) {
        m.addAttribute("locationModels", planInfoService.getAllLocationModel());
        m.addAttribute("planModel", planInfoService.getPlanModelFromPlanModelString(planModelString));
        return "layouts/admin/pages/updateplan";
    }

    @PostMapping("/updateplan")
    public String updatePlanPost(Model m, @RequestParam(value = "update_title") String title) {
        System.out.println(title);
        return "redirect:/calendar";
    }

}