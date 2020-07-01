package com.bkdn.studentmanagement.repositories;

import java.util.List;

import com.bkdn.studentmanagement.entities.PlanEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PlanRepository extends CrudRepository<PlanEntity, Integer>{
    @Query(value = "Select * from plan p where p.date = ?1", nativeQuery = true)
    List<PlanEntity> findPlanEntitiesByDate(String Date);

    @Query(value = "Select * from plan p where p.title = ?1 and p.date = ?2", nativeQuery = true)
    PlanEntity findPlanEntityByTitle(String title, String date);

    @Transactional
    @Modifying
    @Query(value = "Update plan p set p.title = ?1, p.location_id = ?2, p.date = ?3, p.begin_time = ?4, p.end_time = ?5 where p.id = ?6", nativeQuery = true)
    void UpdatePlan(String title, Integer location_id, String date, String begin_time, String end_time, Integer id);
}