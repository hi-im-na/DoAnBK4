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

    LocalDate localDate = LocalDate.now();
    Integer month = 2;
    Integer year = localDate.getYear();

    @GetMapping("/calendar")
    public String calendar(Model m, @RequestParam(value = "next", required = false) String next) {
        if (next != null)
            month += Integer.parseInt(next);
        if (month == 0) {
            year--;
            month = 12;
        }
        if (month == 13) {
            year++;
            month = 1;
        }
        TableModel tableModel = planInfoService.getTableModelByMonthAndYear(month, year);
        m.addAttribute("tableModel", tableModel);
        return "layouts/admin/pages/calendar";
    }

}