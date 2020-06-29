package com.bkdn.studentmanagement.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DayModel {
    private Integer day;
    private List<PlanModel> planModels;

    
    

    /**
     * @return String return the day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * @return List<PlanModel> return the planModels
     */
    public List<PlanModel> getPlanModels() {
        return planModels;
    }

    /**
     * @param planModels the planModels to set
     */
    public void setPlanModels(List<PlanModel> planModels) {
        this.planModels = planModels;
    }

}