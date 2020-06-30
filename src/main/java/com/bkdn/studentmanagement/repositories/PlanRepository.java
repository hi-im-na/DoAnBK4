package com.bkdn.studentmanagement.repositories;

import java.util.List;

import com.bkdn.studentmanagement.entities.PlanEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<PlanEntity, Integer>{
    @Query(value = "Select * from plan p where p.date = ?1", nativeQuery = true)
    List<PlanEntity> findPlanEntitiesByDate(String Date);
}