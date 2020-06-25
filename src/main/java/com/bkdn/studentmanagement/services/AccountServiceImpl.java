package com.bkdn.studentmanagement.services;

import com.bkdn.studentmanagement.entities.AccountEntity;
import com.bkdn.studentmanagement.models.AccountModel;
import com.bkdn.studentmanagement.repositories.AccountRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addNewAccount(AccountModel accountModel) {
        AccountEntity accountEntity = new AccountEntity();

        BeanUtils.copyProperties(accountModel, accountEntity);

        this.accountRepository.save(accountEntity);
        // TODO Auto-generated method stub

    }

    @Override
    public AccountModel findOneByEmail(String email) {
        AccountEntity accountEntity = this.accountRepository.findOneByEmail(email);
        AccountModel accountModel = new AccountModel();
        BeanUtils.copyProperties(accountEntity, accountModel);
        return accountModel;
        // TODO Auto-generated method stub
    }
    
}