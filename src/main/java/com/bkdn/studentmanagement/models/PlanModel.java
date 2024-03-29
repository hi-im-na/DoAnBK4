package com.bkdn.studentmanagement.models;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlanModel {
    private Integer id;
    private String title;
    private Integer locationId;
    private LocalDate date;
    private LocalTime beginTime;
    private LocalTime endTime;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public PlanModel(String title ,Integer locationId, LocalDate localDate, LocalTime beginTime, LocalTime endTime){
        this.title = title;
        this.locationId = locationId;
        this.date = localDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public PlanModel(Integer locationId, LocalTime beginTime, LocalTime endTime)
    {
        this.locationId = locationId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    
    

  



}