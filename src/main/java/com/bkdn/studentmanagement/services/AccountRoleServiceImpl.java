package com.bkdn.studentmanagement.services;

import com.bkdn.studentmanagement.entities.AccountRoleEntity;
import com.bkdn.studentmanagement.models.AccountRoleModel;
import com.bkdn.studentmanagement.repositories.AccountRoleRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    @Autowired 
    private AccountRoleRepository accountRoleRepository;


    @Override
    public void addNewAccountRole(AccountRoleModel accountRoleModel) {
        AccountRoleEntity accountRoleEntity = new AccountRoleEntity();

        BeanUtils.copyProperties(accountRoleModel, accountRoleEntity);
        
        this.accountRoleRepository.save(accountRoleEntity);
        // TODO Auto-generated method stub

    }
    
}