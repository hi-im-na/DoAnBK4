package com.bkdn.studentmanagement.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.bkdn.studentmanagement.entities.AccountPlanEntity;
import com.bkdn.studentmanagement.entities.LocationEntity;
import com.bkdn.studentmanagement.entities.PlanEntity;
import com.bkdn.studentmanagement.models.AccountPlanModel;
import com.bkdn.studentmanagement.models.DayModel;
import com.bkdn.studentmanagement.models.LocationModel;
import com.bkdn.studentmanagement.models.PlanModel;
import com.bkdn.studentmanagement.models.TableModel;
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

    @Override
    public LocationModel getLocationModelById(Integer id) {
        LocationEntity locationEntity = locationRepository.overrideFindById(id);
        LocationModel locationModel = new LocationModel();
        BeanUtils.copyProperties(locationEntity, locationModel);
        return locationModel;
    }

    @Override
    public List<LocationModel> convertLocationEntitiesToModels(List<LocationEntity> locationEntities) {
        List<LocationModel> locationModels = new ArrayList<LocationModel>();
        for (LocationEntity locationEntity : locationEntities) {
            LocationModel locationModel = new LocationModel();
            BeanUtils.copyProperties(locationEntity, locationModel);
            locationModels.add(locationModel);
        }
        return locationModels;
    }

    @Override
    public List<LocationModel> getAllLocationModel() {
        List<LocationEntity> locationEntities = (List<LocationEntity>) locationRepository.findAll();
        List<LocationModel> locationModels = this.convertLocationEntitiesToModels(locationEntities);
        return locationModels;
    }

    @Override
    public String getLocationNameById(Integer id) {
        for (LocationModel locationModel : this.getAllLocationModel()) {
            if (locationModel.getId() == id)
                return locationModel.getLocationName();
        }
        return null;
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
        if (planEntities == null)
            return null;
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

        List<PlanModel> planModels = new ArrayList<PlanModel>();
        if (Date != null) {
            List<PlanEntity> planEntities = planRepository.findPlanEntitiesByDate(Date.toString());
            planModels = this.convertEntitiesToModels(planEntities);
        }
        return planModels;
    }
    
    @Override
    public PlanModel getPlanModelByTitle(List<PlanModel> planModels, String title) {
        for(PlanModel planModel : planModels)
        {
            if(planModel.getTitle() == title)   return planModel;
        }

        return null;
    }

    @Override
    public PlanModel getPlanModelByTitleAndDate(LocalDate date, String title)
    {
        List<PlanModel> planModels = this.getPlanModelsByDate(date);
        PlanModel planModel = this.getPlanModelByTitle(planModels, title);
        return planModel;
    }

    @Override
    public PlanModel getPlanModelFromPlanModelString(String planModelString)
    {
        String date = planModelString.substring(0,10);
        String timeIn = planModelString.substring(10, 15);
        String timeOut = planModelString.substring(15, 20);
        String remainder = planModelString.substring(21);
        String locationId = remainder.substring(0, remainder.indexOf(" "));
        String title = remainder.substring(remainder.indexOf(" ") + 1);

        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTimeIn = LocalTime.parse(timeIn);
        LocalTime localTimeOut = LocalTime.parse(timeOut);
        return new PlanModel(title, Integer.parseInt(locationId), localDate, localTimeIn, localTimeOut);
    }

    // AccountPlanModel
    @Override
    public void addNewAccountPlan(AccountPlanModel accountPlanModel) {
        AccountPlanEntity accountPlanEntity = new AccountPlanEntity();

        BeanUtils.copyProperties(accountPlanModel, accountPlanEntity);

        this.accountPlanRepository.save(accountPlanEntity);

    }

    // DayModel
    @Override
    public DayModel getDayModel(LocalDate localDate) {
        List<PlanModel> planModels = this.getPlanModelsByDate(localDate);
        Integer day = 0;
        if (localDate != null)
            day = localDate.getDayOfMonth();
        return new DayModel(day, planModels);
    }

    // TableModel
    @Override
    public Integer getDaysInMonth(Integer month, Integer year) {
        switch (month) {
            case 1:
                return 31;
            case 2: {
                if (year % 4 == 0) {
                    if (year % 100 == 0) {
                        if (year % 400 == 0) {
                            return 29;
                        } else
                            return 28;
                    } else
                        return 29;
                }
                return 28;
            }
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    @Override
    public Integer getDOWByDay1(Integer month, Integer year) {
        LocalDate localDate = LocalDate.of(year, month, 1);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.getValue() + 1;
    }

    @Override
    public Integer getFixDayTop(Integer DOWByDay1) {
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
    public Integer getFixDayBot(Integer fixDayTop, Integer daysInMonth) {
        return ((fixDayTop + daysInMonth) % 7 == 0) ? 0 : 7 - (fixDayTop + daysInMonth) % 7;
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

    @Override
    public List<List<DayModel>> handleCalendar(Integer daysInMonth, Integer fixDayBot, Integer fixDayTop, Integer month,
            Integer year) {
        List<List<DayModel>> listWeeks = new ArrayList<List<DayModel>>();
        Integer daysInWeek = 7;
        Integer numOfRow = (daysInMonth + fixDayBot + fixDayTop) / daysInWeek;
        Integer day = 1;
        for (int row = 0; row < numOfRow; row++) {
            List<DayModel> listDays = new ArrayList<DayModel>();
            for (int col = 0; col < daysInWeek; col++) {
                if (fixDayTop > 0) {
                    LocalDate localDate = null;
                    DayModel dayModel = getDayModel(localDate);
                    listDays.add(dayModel);
                    fixDayTop--;
                } else if (day <= daysInMonth) {
                    LocalDate localDate = LocalDate.of(year, month, day);
                    DayModel dayModel = getDayModel(localDate);
                    listDays.add(dayModel);
                    day++;
                } else if (fixDayBot > 0) {
                    LocalDate localDate = null;
                    DayModel dayModel = getDayModel(localDate);
                    listDays.add(dayModel);
                    fixDayBot--;
                }

            }
            listWeeks.add(listDays);
        }
        return listWeeks;
    }

    @Override
    public TableModel getTableModelByMonthAndYear(Integer month, Integer year) {
        Integer daysInMonth = this.getDaysInMonth(month, year);
        Integer firstDay = this.getDOWByDay1(month, year);
        Integer fixDayTop = this.getFixDayTop(firstDay);
        Integer fixDayBot = this.getFixDayBot(fixDayTop, daysInMonth);
        String monthString = this.monthToString(month);
        List<LocationModel> locationModels = this.getAllLocationModel();
        List<List<DayModel>> listWeeks = this.handleCalendar(daysInMonth, fixDayBot, fixDayTop, month, year);
        return new TableModel(month, monthString, year, locationModels, listWeeks);
    }



}

// @Override
// public String monthToString(Integer month) {
// switch (month) {
// case 1:
// return "January";
// case 2:
// return "February";
// case 3:
// return "March";
// case 4:
// return "April";
// case 5:
// return "May";
// case 6:
// return "June";
// case 7:
// return "July";
// case 8:
// return "August";
// case 9:
// return "September";
// case 10:
// return "October";
// case 11:
// return "November";
// default:
// return "December";
// }
// }

// } public String monthToString(Integer month) {
// switch (month) {
// case 1:
// return "January";
// case 2:
// return "February";
// case 3:
// return "March";
// case 4:
// return "April";
// case 5:
// return "May";
// case 6:
// return "June";
// case 7:
// return "July";
// case 8:
// return "August";
// case 9:
// return "September";
// case 10:
// return "October";
// case 11:
// return "November";
// default:
// return "December";
// }
// }

// }