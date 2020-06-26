package com.bkdn.studentmanagement.services;

import com.bkdn.studentmanagement.models.AccountPlanModel;
import com.bkdn.studentmanagement.models.LocationModel;
import com.bkdn.studentmanagement.models.PlanModel;

public interface PlanInfoService {
    //Account
    public void addNewLocation(LocationModel locationModel);

    //Plan
    public void addNewPlan(PlanModel planModel);

    //AccountPlan
    public void addNewAccountPlan(AccountPlanModel accountPlanModel);
}