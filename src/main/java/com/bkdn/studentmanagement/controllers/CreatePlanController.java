package com.bkdn.studentmanagement.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.bkdn.studentmanagement.models.LocationModel;
import com.bkdn.studentmanagement.models.PlanModel;
import com.bkdn.studentmanagement.services.PlanInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreatePlanController {

    @Autowired
    PlanInfoService planInfoService;

    @GetMapping("/newplan")
    public String newPlanGet(Model m) {
        List<LocationModel> locationModels = planInfoService.getAllLocationModel();
        m.addAttribute("locationModels", locationModels);
        return "layouts/admin/pages/newplan";
    }

    @PostMapping("/newplan")
    public String newPlanPost(@RequestParam(value = "day") String day, @RequestParam(value = "month") String month,
            @RequestParam(value = "year") String year, @RequestParam(value = "title") String title,
            @RequestParam(value = "timeIn") String timeIn, @RequestParam(value = "timeOut") String timeOut,
            @RequestParam(value = "location") String locationId) {
        LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        LocalTime localTimeIn = LocalTime.parse(timeIn);
        LocalTime localTimeOut = LocalTime.parse(timeOut);
        List<PlanModel> planModels = planInfoService.getPlanModelsByDate(localDate);
        for (PlanModel planModel : planModels) 
        {
            if(Integer.parseInt(locationId) == planModel.getLocationId())
            {
                if(localTimeIn.compareTo(planModel.getBeginTime()) >= 0 && localTimeIn.compareTo(planModel.getEndTime()) < 0)
                {
                    return "redirect:/newplan?error=true";
                }
                if(localTimeOut.compareTo(planModel.getBeginTime()) > 0 && localTimeIn.compareTo(planModel.getEndTime()) <= 0)
                {
                    return "redirect:/newplan?error=true";
                }
            }

        }
        planInfoService.addNewPlan(new PlanModel(Integer.parseInt(locationId), localDate, localTimeIn, localTimeOut));

        return "redirect:/calendar";
    }
}