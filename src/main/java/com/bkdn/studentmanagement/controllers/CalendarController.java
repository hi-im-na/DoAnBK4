package com.bkdn.studentmanagement.controllers;

import java.util.List;
import java.util.Vector;

import com.bkdn.studentmanagement.models.DayModel;
import com.bkdn.studentmanagement.models.LocationModel;
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
        TableModel tableModel = planInfoService.getTableModelByMonthAndYear(month, year);
        List<List<DayModel>> listWeeks = tableModel.getListWeeks();
        for(int i = 0; i < listWeeks.size(); i++)
        {
            for(int j = 0; j < listWeeks.get(i).size(); j++)
            {
                System.out.println(listWeeks.get(i).get(j).getDay());
                for(int k = 0; k < listWeeks.get(i).get(j).getPlanModels().size(); k++)
                {
                    System.out.println(tableModel.getLocationNameById(listWeeks.get(i).get(j).getPlanModels().get(k).getLocationId()));
                    System.out.println(listWeeks.get(i).get(j).getPlanModels().get(k).getBeginTime());
                    System.out.println(listWeeks.get(i).get(j).getPlanModels().get(k).getEndTime());
                }
            }
        }
        m.addAttribute("tableModel", tableModel);
        return "layouts/admin/pages/calendar";
    }
}