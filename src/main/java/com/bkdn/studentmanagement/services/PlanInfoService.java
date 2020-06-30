package com.bkdn.studentmanagement.services;

import java.time.LocalDate;
import java.util.List;

import com.bkdn.studentmanagement.entities.LocationEntity;
import com.bkdn.studentmanagement.entities.PlanEntity;
import com.bkdn.studentmanagement.models.AccountPlanModel;
import com.bkdn.studentmanagement.models.DayModel;
import com.bkdn.studentmanagement.models.LocationModel;
import com.bkdn.studentmanagement.models.PlanModel;
import com.bkdn.studentmanagement.models.TableModel;

public interface PlanInfoService {

    // Location
    public void addNewLocation(LocationModel locationModel);

    public LocationModel getLocationModelById(Integer id);

    public List<LocationModel> convertLocationEntitiesToModels(List<LocationEntity> locationEntities);

    public List<LocationModel> getAllLocationModel();


    // Plan
    public void addNewPlan(PlanModel planModel);

    public List<PlanModel> convertEntitiesToModels(List<PlanEntity> planEntities);

    public List<PlanModel> getPlanModelsByDate(LocalDate Date);

    // AccountPlan
    public void addNewAccountPlan(AccountPlanModel accountPlanModel);

    // DayModel
    public DayModel getDayModel(LocalDate localDate);

    // TableModel
    public Integer getDaysInMonth(Integer month, Integer year);

    public Integer getDOWByDay1(Integer month, Integer year);

    public Integer getFixDayTop(Integer DOWByDay1);

    public Integer getFixDayBot(Integer fixDayTop, Integer daysInMonth);

    public String monthToString(Integer month);

    public List<List<DayModel>> handleCalendar(Integer daysInMonth, Integer fixDayBot, Integer FixDayTop, Integer month,
            Integer year);

    public TableModel getTableModelByMonthAndYear(Integer month, Integer year);
}