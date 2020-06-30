package com.bkdn.studentmanagement.controllers;

import java.time.LocalDate;
import java.util.Calendar;

import com.bkdn.studentmanagement.models.TableModel;
import com.bkdn.studentmanagement.services.PlanInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalendarController {

    @Autowired
    PlanInfoService planInfoService;

    Integer month = 6;
    Integer next = -1; 
    
    @GetMapping("/calendar")
    public String calendar (Model m){
        LocalDate localDate = LocalDate.now();
        Calendar calendar = Calendar.getInstance();
        calendar.set(localDate.getYear(), localDate.getMonthValue(), 1);
        if(month != null && next != null)
        {
            calendar.add(Calendar.MONTH, (month - calendar.get(Calendar.MONTH)) + next);
        } 
        TableModel tableModel = planInfoService.getTableModelByMonthAndYear(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        m.addAttribute("tableModel", tableModel);
        return "layouts/admin/pages/calendar";
    }
    Calendar calendar = Calendar.getInstance();
}