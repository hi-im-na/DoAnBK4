package com.bkdn.studentmanagement.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bkdn.studentmanagement.entities.AccountRoleEntity;

public interface AccountRoleRepository extends CrudRepository<AccountRoleEntity, Integer> {
    public List<AccountRoleEntity> findByAccountId(int accountId);
}
