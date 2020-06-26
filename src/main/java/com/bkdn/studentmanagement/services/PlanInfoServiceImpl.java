package com.bkdn.studentmanagement.services;

import com.bkdn.studentmanagement.entities.AccountPlanEntity;
import com.bkdn.studentmanagement.entities.LocationEntity;
import com.bkdn.studentmanagement.entities.PlanEntity;
import com.bkdn.studentmanagement.models.AccountPlanModel;
import com.bkdn.studentmanagement.models.LocationModel;
import com.bkdn.studentmanagement.models.PlanModel;
import com.bkdn.studentmanagement.repositories.AccountPlanRepository;
import com.bkdn.studentmanagement.repositories.LocationRepository;
import com.bkdn.studentmanagement.repositories.PlanRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanInfoServiceImpl implements PlanInfoService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private AccountPlanRepository accountPlanRepository;

// LocationModel
    @Override
    public void addNewLocation(LocationModel locationModel) {
        LocationEntity locationEntity = new LocationEntity();

        BeanUtils.copyProperties(locationModel, locationEntity);

        this.locationRepository.save(locationEntity);
        // TODO Auto-generated method stub

    }

// PlanModel
    @Override
    public void addNewPlan(PlanModel planModel) {
        PlanEntity planEntity = new PlanEntity();
        
        BeanUtils.copyProperties(planModel, planEntity);

        this.planRepository.save(planEntity);
        // TODO Auto-generated method stub

    }

// AccountPlanModel
    @Override
    public void addNewAccountPlan(AccountPlanModel accountPlanModel) {
        AccountPlanEntity accountPlanEntity = new AccountPlanEntity();

        BeanUtils.copyProperties(accountPlanModel, accountPlanEntity);

        this.accountPlanRepository.save(accountPlanEntity);
        // TODO Auto-generated method stub

    }



}