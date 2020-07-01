package com.bkdn.studentmanagement.repositories;

import java.util.List;

import com.bkdn.studentmanagement.entities.AccountPlanEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountPlanRepository extends CrudRepository<AccountPlanEntity, Integer> {
    @Query(value = "Select * from account_plan ap where ap.plan_id = ?1", nativeQuery = true)
    List<AccountPlanEntity> findAllByPlanId(Integer plan_id);
}