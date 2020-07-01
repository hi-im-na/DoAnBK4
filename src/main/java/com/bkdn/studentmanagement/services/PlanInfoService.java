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

    public String getLocationNameById(Integer id);


    // Plan
    public void addNewPlan(PlanModel planModel);

    public void deletePlanModel(PlanModel planModel);

    public void deletePlanModelById(Integer plan_id);

    public void UpdatePlanModelById(String title, Integer location_id, String date, String begin_time, String end_time, Integer id);

    public PlanModel findPlanModelByTitle(String title, String date);

    public List<PlanModel> convertEntitiesToModels(List<PlanEntity> planEntities);

    public List<PlanModel> getPlanModelsByDate(LocalDate Date);

    public PlanModel getPlanModelByTitle(List<PlanModel> planModels, String title);

    public PlanModel getPlanModelByTitleAndDate(LocalDate date, String title);

    public PlanModel getPlanModelFromPlanModelString(String planModelString);

    // AccountPlan
    public void addNewAccountPlan(AccountPlanModel accountPlanModel);
    
    public void deleteAccountPlanModelsByPlanId(Integer planId);



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