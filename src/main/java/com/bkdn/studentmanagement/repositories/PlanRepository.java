package com.bkdn.studentmanagement.repositories;

import com.bkdn.studentmanagement.entities.PlanEntity;

import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<PlanEntity, Integer>{
    
}