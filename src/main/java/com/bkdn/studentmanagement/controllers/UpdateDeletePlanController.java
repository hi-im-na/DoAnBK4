package com.bkdn.studentmanagement.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
            @RequestParam(value = "root_title") String root_title, @RequestParam(value = "location") String locationId,
            @RequestParam(value = "update_date") String date,
            @RequestParam(value = "update_beginTime") String beginTime,
            @RequestParam(value = "update_endTime") String endTime,
            @RequestParam(value = "update_delete") String update_date) {

        beginTime = beginTime.substring(0, 5);
        endTime = endTime.substring(0, 5);
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTimeIn = LocalTime.parse(beginTime);
        LocalTime localTimeOut = LocalTime.parse(endTime);
        PlanModel planModel_root = planInfoService.findPlanModelByTitle(root_title,date);
        PlanModel planModel1 = planInfoService.findPlanModelByTitle(title,date);

        List<PlanModel> planModels = planInfoService.getPlanModelsByDate(localDate);
        if (localTimeIn.compareTo(localTimeOut) >= 0) {
            return "redirect:/calendar?error=updateFailed";
        }
        if (planModels != null)
            for (PlanModel planModel : planModels) {
                if (title.equals(planModel.getTitle()) && planModel_root.getId() != planModel.getId()) {
                    System.out.println("loi trung title");
                    return "redirect:/calendar?error=updateFailed";
                }

                if (Integer.parseInt(locationId) == planModel.getLocationId()) {
                    if (planModel != null) {
                        if (localTimeIn.compareTo(planModel.getBeginTime()) >= 0
                                && localTimeIn.compareTo(planModel.getEndTime()) < 0
                                && planModel_root.getId() != planModel.getId()) {
                            System.out.println("tg vao");
                            return "redirect:/calendar?error=updateFailed";
                        }
                        if (localTimeOut.compareTo(planModel.getBeginTime()) > 0
                                && localTimeIn.compareTo(planModel.getEndTime()) <= 0
                                && planModel_root.getId() != planModel.getId()) {
                            System.out.println("tg ra");
                            return "redirect:/calendar?error=updateFailed";
                        }
                    }
                }
            }

        if (planModel1 != null && !root_title.equals(title)) {
            return "redirect:/calendar?error=updateFailed";
        }

        planInfoService.UpdatePlanModelById(title, Integer.parseInt(locationId), date, beginTime, endTime,
                planModel_root.getId());
        return "redirect:/calendar";
    }

}