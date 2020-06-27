package com.bkdn.studentmanagement.services;

import java.util.Calendar;

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

    }

    // PlanModel
    @Override
    public void addNewPlan(PlanModel planModel) {
        PlanEntity planEntity = new PlanEntity();

        BeanUtils.copyProperties(planModel, planEntity);

        this.planRepository.save(planEntity);

    }

    // AccountPlanModel
    @Override
    public void addNewAccountPlan(AccountPlanModel accountPlanModel) {
        AccountPlanEntity accountPlanEntity = new AccountPlanEntity();

        BeanUtils.copyProperties(accountPlanModel, accountPlanEntity);

        this.accountPlanRepository.save(accountPlanEntity);

    }

    // TableModel
    @Override
    public Integer getDaysInMonth(Integer month, Integer year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        System.out.println("**********"+calendar.get(Calendar.DAY_OF_MONTH) + "**********");
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Integer getDOWByDay1(Integer month, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public Integer getFixDay(Integer DOWByDay1) {
        switch (DOWByDay1) {
            case 2: return 0;
            case 3: return 1;
            case 4: return 2;
            case 5: return 3;
            case 6: return 4;
            case 7: return 5;
            default: return 6;
        }
    }





}