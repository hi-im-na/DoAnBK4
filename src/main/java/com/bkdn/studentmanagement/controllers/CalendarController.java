package com.bkdn.studentmanagement.controllers;

import com.bkdn.studentmanagement.models.TableModel;
import com.bkdn.studentmanagement.services.PlanInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @Autowired
    PlanInfoService planInfoService;

    Integer month = 3, year = 2020;
    @GetMapping("/calendar")
    public String calendar (Model m){

        Integer daysInMonth = planInfoService.getDaysInMonth(month, year);
        Integer firstDay = planInfoService.getDOWByDay1(month, year);
        Integer fixDay = planInfoService.getFixDay(firstDay);
        String monthString = planInfoService.monthToString(month);
        TableModel tableModel = new TableModel(daysInMonth, monthString, year,fixDay);
        m.addAttribute("tableModel", tableModel);
        return "layouts/admin/pages/calendar";
    }
}