package com.bkdn.studentmanagement.models;

import java.util.List;
import java.util.Vector;

import org.springframework.data.util.Pair;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TableModel {
    private Integer month;
    private String monthString;
    private Integer year;
    private List<LocationModel> locationModels;
    private List<List<DayModel>> listWeeks;

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

    public List<LocationModel> getLocationModels() {
        return locationModels;
    }

    public void setLocationModels(List<LocationModel> locationModels) {
        this.locationModels = locationModels;
    }

    /**
     * @return List<List<DayModel>> return the listWeeks
     */
    public List<List<DayModel>> getListWeeks() {
        return listWeeks;
    }

    /**
     * @param listWeeks the listWeeks to set
     */
    public void setListWeeks(List<List<DayModel>> listWeeks) {
        this.listWeeks = listWeeks;
    }

    public String getLocationNameById(Integer id) {
        for (LocationModel locationModel : this.locationModels) {
            if (locationModel.getId() == id)
                return locationModel.getLocationName();
        }
        return null;
    }

}