package com.bkdn.studentmanagement.controllers;

import com.bkdn.studentmanagement.models.LocationModel;
import com.bkdn.studentmanagement.models.PlanModel;
import com.bkdn.studentmanagement.services.PlanInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeletePlanController {

    @Autowired
    PlanInfoService planInfoService;

    @GetMapping("/deleteplan")
    public String delete(@RequestParam(value = "delete") String planModelString, Model m) {
        PlanModel planModel = planInfoService.getPlanModelFromPlanModelString(planModelString);
        m.addAttribute("locationName", planInfoService.getLocationNameById(planModel.getLocationId()));
        m.addAttribute("planModel", planModel);
        // planInfoService.deleteAccountPlanModelsByPlanId(planModel.getId());
        // planInfoService.deletePlanModelById(planModel.getId());
        return "layouts/admin/pages/deleteplan";
    }

    @PostMapping(value = "/deleteplan")
    public String deletePost(@RequestParam("delete_title") String title,
            @RequestParam("delete_beginTime") String beginTime, @RequestParam("delete_endTime") String endTime,
            @RequestParam("delete_date") String date, @RequestParam("delete_location") String location) {
        System.out.println(title);
        PlanModel planModel = planInfoService.findPlanModelByTitle(title, date);
        planInfoService.deleteAccountPlanModelsByPlanId(planModel.getId());
        planInfoService.deletePlanModelById(planModel.getId());

        return "redirect:/calendar";
    }

}