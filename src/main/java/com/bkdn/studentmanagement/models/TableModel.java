package com.bkdn.studentmanagement.models;

import java.util.List;
import java.util.Vector;

import org.springframework.data.util.Pair;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TableModel {
    private Integer fixDay;
    private Integer daysInMonth;
    private Integer month;
    private String monthString;
    private Integer year;
    private Vector<Pair<Integer, List<PlanModel>>> plansInMonth;

    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getMonthString() {
        return this.monthString;
    }

    public void setMonthString(String monthString) {
        this.monthString = monthString;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDaysInMonth() {
        return this.daysInMonth;
    }

    public void setDaysInMonth(Integer daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    public Integer getFixDay() {
        return this.fixDay;
    }

    public void setFixDay(Integer fixDay) {
        this.fixDay = fixDay;
    }

    public Vector<Pair<Integer, List<PlanModel>>> getPlansInMonth() {
        return plansInMonth;
    }

    public void setPlansInMonth(Vector<Pair<Integer, List<PlanModel>>> plansInMonth) {
        this.plansInMonth = plansInMonth;
    }

    public TableModel(Integer daysInMonth, String monthString, Integer year, Integer fixDay) {
        this.daysInMonth = daysInMonth;
        this.monthString = monthString;
        this.year = year;
        this.fixDay = fixDay;
    }

}