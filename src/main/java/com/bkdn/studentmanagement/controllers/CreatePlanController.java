package com.bkdn.studentmanagement.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.bkdn.studentmanagement.models.LocationModel;
import com.bkdn.studentmanagement.models.PlanModel;
import com.bkdn.studentmanagement.services.PlanInfoService;

import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam(value = "month") String month, @RequestParam(value = "year") String year) {
        List<LocationModel> locationModels = planInfoService.getAllLocationModel();
        LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        System.out.println(date.toString());
        m.addAttribute("date", date);
        m.addAttribute("locationModels", locationModels);
        return "layouts/admin/pages/newplan";
    }

    @PostMapping("/newplan")
    public String newPlanPost(@RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "timeIn") String timeIn, @RequestParam(value = "timeOut") String timeOut,
            @RequestParam(value = "location") String locationId, Model m) {
        System.out.println(locationId);
        LocalDate localDate = LocalDate.parse(date);
        System.out.println(localDate.toString());
        LocalTime localTimeIn = LocalTime.parse(timeIn);
        LocalTime localTimeOut = LocalTime.parse(timeOut);
        List<PlanModel> planModels = planInfoService.getPlanModelsByDate(localDate);
        if (localTimeIn.compareTo(localTimeOut) >= 0)
            return "redirect:/newplan?error=true";
        for (PlanModel planModel : planModels) {
            if (Integer.parseInt(locationId) == planModel.getLocationId()) {
                if (localTimeIn.compareTo(planModel.getBeginTime()) >= 0
                        && localTimeIn.compareTo(planModel.getEndTime()) < 0) {
                    return "redirect:/newplan?error=true";
                }
                if (localTimeOut.compareTo(planModel.getBeginTime()) > 0
                        && localTimeIn.compareTo(planModel.getEndTime()) <= 0) {
                    return "redirect:/newplan?error=true";
                }
            }

        }
        planInfoService.addNewPlan(new PlanModel(Integer.parseInt(locationId), localDate, localTimeIn, localTimeOut));
        return "redirect:/calendar";
    }

    // @GetMapping("/newplancf")
    // public String newplancf(@RequestParam(value = "day") String day,
    // @RequestParam(value = "month") String month,
    // @RequestParam(value = "year") String year, @RequestParam(value = "timeIn")
    // String timeIn,
    // @RequestParam(value = "timeOut") String timeOut, @RequestParam(value =
    // "location") String locationId,
    // Model m) {
    // LocalDate localDate = LocalDate.of(Integer.parseInt(year),
    // Integer.parseInt(month), Integer.parseInt(day));
    // m.addAttribute("planModel",
    // new PlanModel(Integer.parseInt(locationId), localDate,
    // LocalTime.parse(timeIn), LocalTime.parse(timeOut)));
    // return "layouts/admin/pages/newplancf";
    // }

    // @PostMapping("/newplancf")
    // public String newplancfPost(@RequestParam(value = "Date") String date,
    // @RequestParam(value = "timeIn") String timeIn,
    // @RequestParam(value = "timeOut") String timeOut, @RequestParam(value =
    // "location") String locationId,
    // Model m) {
    // LocalDate localDate = LocalDate.parse(date);
    // LocalTime localTimeIn = LocalTime.parse(timeIn);
    // LocalTime localTimeOut = LocalTime.parse(timeOut);
    // List<PlanModel> planModels = planInfoService.getPlanModelsByDate(localDate);
    // if (localTimeIn.compareTo(localTimeOut) >= 0)
    // return "redirect:/newplan?error=true";
    // for (PlanModel planModel : planModels) {
    // if (Integer.parseInt(locationId) == planModel.getLocationId()) {
    // if (localTimeIn.compareTo(planModel.getBeginTime()) >= 0
    // && localTimeIn.compareTo(planModel.getEndTime()) < 0) {
    // return "redirect:/newplan?error=true";
    // }
    // if (localTimeOut.compareTo(planModel.getBeginTime()) > 0
    // && localTimeIn.compareTo(planModel.getEndTime()) <= 0) {
    // return "redirect:/newplan?error=true";
    // }
    // }

    // }
    // planInfoService.addNewPlan(new PlanModel(Integer.parseInt(locationId),
    // localDate, localTimeIn, localTimeOut));
    // return "redirect:/calendar";
    // }
}