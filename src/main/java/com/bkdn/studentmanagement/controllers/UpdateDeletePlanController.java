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
    public String updatePlanPost(Model m, @RequestParam(value = "update_title") String title,
            @RequestParam(value = "location") String locationId, @RequestParam(value = "update_date") String date,
            @RequestParam(value = "update_beginTime") String beginTime,
            @RequestParam(value = "update_endTime") String endTime,
            @RequestParam(value = "update_delete") String update_date) {

        PlanModel planModel = planInfoService.findPlanModelByTitle(title);
        if(update_date.equals("delete")){
            planInfoService.deleteAccountPlanModelsByPlanId(planModel.getId());
            planInfoService.deletePlanModelById(planModel.getId());
        }else {
            planInfoService.UpdatePlanModelById(title, Integer.parseInt(locationId), date, beginTime, endTime, planModel.getId());
        }
        return "redirect:/calendar";
    }

}