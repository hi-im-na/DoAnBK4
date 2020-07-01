package com.bkdn.studentmanagement.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.bkdn.studentmanagement.configs.models.AccountModel;
import com.bkdn.studentmanagement.configs.models.structures.AccountInfo;
import com.bkdn.studentmanagement.models.AccountPlanModel;
import com.bkdn.studentmanagement.models.LocationModel;
import com.bkdn.studentmanagement.models.PlanModel;
import com.bkdn.studentmanagement.services.PlanInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CreatePlanController {

    @Autowired
    PlanInfoService planInfoService;

    @GetMapping("/newplan")
    public String newPlanGet(Model m, @RequestParam(value = "day") String day,
            @RequestParam(value = "month") String month,
            @RequestParam(value = "year") String year) {
        List<LocationModel> locationModels = planInfoService.getAllLocationModel();
        LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        System.out.println(date.toString());
        m.addAttribute("date", date);
        m.addAttribute("locationModels", locationModels);
        return "layouts/admin/pages/newplan";
    }

    @PostMapping("/newplan")
    public String newPlanPost(@RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "timeIn", required = false) String timeIn,
            @RequestParam(value = "timeOut", required = false) String timeOut,
            @RequestParam(value = "location", required = false) String locationId, Model m) {

        timeIn = timeIn.substring(0,5);
        timeOut = timeOut.substring(0,5);
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTimeIn = LocalTime.parse(timeIn);
        LocalTime localTimeOut = LocalTime.parse(timeOut);

        List<PlanModel> planModels = planInfoService.getPlanModelsByDate(localDate);
        if (localTimeIn.compareTo(localTimeOut) >= 0) {
            return "redirect:/calendar?error=true";
        } 
        if (planModels != null)
            for (PlanModel planModel : planModels) {
                if(title == planModel.getTitle())   return "redirect:/calendar?error=true";
                if (Integer.parseInt(locationId) == planModel.getLocationId()) {
                    if (planModel != null) {
                        if (localTimeIn.compareTo(planModel.getBeginTime()) >= 0
                                && localTimeIn.compareTo(planModel.getEndTime()) < 0) {
                            return "redirect:/calendar?error=true";
                        }
                        if (localTimeOut.compareTo(planModel.getBeginTime()) > 0
                                && localTimeIn.compareTo(planModel.getEndTime()) <= 0) {     
                            return "redirect:/calendar?error=true";
                        }
                    }
                }
            }

        planInfoService.addNewPlan(new PlanModel(title, Integer.parseInt(locationId), localDate, localTimeIn, localTimeOut));      
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountInfo accountInfo = ((AccountModel) auth.getPrincipal()).getUserInfo();
        PlanModel planModel = planInfoService.getPlanModelByTitleAndDate(localDate, title);
        planInfoService.addNewAccountPlan(new AccountPlanModel(accountInfo.getId(), planModel.getId()));
        System.out.println(accountInfo.getId() + planModel.getId());

        return "redirect:/calendar";
    }
}