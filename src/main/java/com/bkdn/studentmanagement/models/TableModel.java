package com.bkdn.studentmanagement.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import org.springframework.data.util.Pair;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TableModel {
    private Integer month;
    private String monthString;
    private Integer year;   
    private Integer daysInMonth;
    private Integer fixDay;
    private Vector<Pair<Integer, PlanModel>> plans;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDaysInMonth() {
        return daysInMonth;
    }

    public void setDaysInMonth(Integer daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    public Vector<Pair<Integer, PlanModel>> getPlans() {
        return plans;
    }

    public void setPlans(Vector<Pair<Integer, PlanModel>> plans) {
        this.plans = plans;
    }

    public Integer getFixDay() {
        return fixDay;
    }

    public void setFixDay(Integer fixDay) {
        this.fixDay = fixDay;
    }

    public String getMonthString() {
        return monthString;
    }

    public void setMonthString(String monthString) {
        this.monthString = monthString;
    }

    public TableModel(Integer daysInMonth, String monthString, Integer year, Integer fixDay) {
        this.daysInMonth = daysInMonth;
        this.monthString = monthString;
        this.year = year;
        this.fixDay = fixDay;
    }

}