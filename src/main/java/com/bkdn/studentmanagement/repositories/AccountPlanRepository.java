package com.bkdn.studentmanagement.repositories;

import com.bkdn.studentmanagement.entities.AccountPlanEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountPlanRepository extends CrudRepository<AccountPlanEntity, Integer>{
}