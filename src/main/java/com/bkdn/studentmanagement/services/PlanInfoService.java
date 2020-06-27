package com.bkdn.studentmanagement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import com.bkdn.studentmanagement.entities.PlanEntity;
import com.bkdn.studentmanagement.models.AccountPlanModel;
import com.bkdn.studentmanagement.models.LocationModel;
import com.bkdn.studentmanagement.models.PlanModel;

import org.springframework.data.util.Pair;

public interface PlanInfoService {
    //Account
    public void addNewLocation(LocationModel locationModel);

    //Plan
    public void addNewPlan(PlanModel planModel);
    public List<PlanModel> convertEntitiesToModels(List<PlanEntity> planEntities);
    public List<PlanModel> getPlanModelsByDate(LocalDate Date);
    public Vector<Pair<Integer, List<PlanModel> > > getPlanInfosFromDB(Integer daysInMonth, Integer month, Integer year);

    //AccountPlan
    public void addNewAccountPlan(AccountPlanModel accountPlanModel);

    //TableModel
    public Integer getDaysInMonth(Integer month, Integer year);
    public Integer getDOWByDay1(Integer month, Integer year);
    public Integer getFixDay(Integer DOWByDay1);
    public String monthToString(Integer month);
}