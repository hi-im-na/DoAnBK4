package com.bkdn.studentmanagement.controllers;

import java.util.List;
import java.util.Vector;

import com.bkdn.studentmanagement.models.PlanModel;
import com.bkdn.studentmanagement.models.TableModel;
import com.bkdn.studentmanagement.services.PlanInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @Autowired
    PlanInfoService planInfoService;

    Integer month = 6, year = 2020;
    @GetMapping("/calendar")
    public String calendar (Model m){

        Integer daysInMonth = planInfoService.getDaysInMonth(month, year);
        Integer firstDay = planInfoService.getDOWByDay1(month, year);
        Integer fixDay = planInfoService.getFixDay(firstDay);
        String monthString = planInfoService.monthToString(month);
        Vector<Pair<Integer, List<PlanModel>> > plansInMonth = planInfoService.getPlanInfosFromDB(daysInMonth, month, year);
        System.out.println("xxxxxxxx" + plansInMonth.firstElement().getSecond().get(1).getDate().getDayOfMonth() + "xxxxxxxx");
        TableModel tableModel = new TableModel(fixDay, daysInMonth, month, monthString, year, plansInMonth,-1);
        
        m.addAttribute("tableModel", tableModel);
        return "layouts/admin/pages/calendar";
    }
}