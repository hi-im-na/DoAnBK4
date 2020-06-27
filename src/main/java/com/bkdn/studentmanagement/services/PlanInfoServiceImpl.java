package com.bkdn.studentmanagement.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

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
import org.springframework.data.util.Pair;
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

    @Override
    public List<PlanModel> convertEntitiesToModels(List<PlanEntity> planEntities) {
        List<PlanModel> planModels = new ArrayList<PlanModel>();
        for (PlanEntity planEntity : planEntities) {
            PlanModel planModel = new PlanModel();
            BeanUtils.copyProperties(planEntity, planModel);
            planModels.add(planModel);
        }
        return planModels;
    }

    @Override
    public List<PlanModel> getPlanModelsByDate(LocalDate Date) {
        List<PlanEntity> planEntities = planRepository.findPlanEntitiesByDate(Date.toString());
        List<PlanModel> planModels = this.convertEntitiesToModels(planEntities);
        return planModels;
    }

    @Override
    public Vector<Pair<Integer, List<PlanModel>>> getPlanInfosFromDB(Integer daysInMonth, Integer month, Integer year) {
        Vector<Pair<Integer, List<PlanModel>>> plansInMonth = new Vector<Pair<Integer, List<PlanModel>>>();
        for (int dayOfMonth = 1; dayOfMonth <= daysInMonth; dayOfMonth++) {
            //ngay can lay
            LocalDate date = LocalDate.of(year, month, dayOfMonth);
            //danh sach ngay can lay 
            List<PlanModel> planModels = this.getPlanModelsByDate(date);

            if(planModels.size() != 0)  
            {
                plansInMonth.add(Pair.of(dayOfMonth, planModels));
            }
        }
        return plansInMonth;
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
    public Integer getDaysInMonth(Integer month, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        System.out.println("**********" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + "**********");
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
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
            case 2:
                return 0;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 4;
            case 7:
                return 5;
            default:
                return 6;
        }
    }

    @Override
    public String monthToString(Integer month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            default:
                return "December";
        }
    }

    }

//     @Override
//     public String monthToString(Integer month) {
//         switch (month) {
//             case 1:
//                 return "January";
//             case 2:
//                 return "February";
//             case 3:
//                 return "March";
//             case 4:
//                 return "April";
//             case 5:
//                 return "May";
//             case 6:
//                 return "June";
//             case 7:
//                 return "July";
//             case 8:
//                 return "August";
//             case 9:
//                 return "September";
//             case 10:
//                 return "October";
//             case 11:
//                 return "November";
//             default:
//                 return "December";
//         }
//     }




// }   public String monthToString(Integer month) {
    //     switch (month) {
    //         case 1:
    //             return "January";
    //         case 2:
    //             return "February";
    //         case 3:
    //             return "March";
    //         case 4:
    //             return "April";
    //         case 5:
    //             return "May";
    //         case 6:
    //             return "June";
    //         case 7:
    //             return "July";
    //         case 8:
    //             return "August";
    //         case 9:
    //             return "September";
    //         case 10:
    //             return "October";
    //         case 11:
    //             return "November";
    //         default:
    //             return "December";
    //     }
    // }






// }