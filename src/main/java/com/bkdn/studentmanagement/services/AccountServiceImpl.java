package com.bkdn.studentmanagement.services;

import java.util.ArrayList;
import java.util.List;

import com.bkdn.studentmanagement.configs.models.structures.AccountInfo;
import com.bkdn.studentmanagement.entities.AccountEntity;
import com.bkdn.studentmanagement.entities.AccountRoleEntity;
import com.bkdn.studentmanagement.entities.RoleEntity;
import com.bkdn.studentmanagement.models.AccountModel;
import com.bkdn.studentmanagement.models.AccountRoleModel;
import com.bkdn.studentmanagement.models.RoleModel;
import com.bkdn.studentmanagement.repositories.AccountRepository;
import com.bkdn.studentmanagement.repositories.AccountRoleRepository;
import com.bkdn.studentmanagement.repositories.RoleRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    // AccountModel
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

    @Override
    public List<AccountModel> findAllAccounts() {
        List<AccountEntity> accountEntities = (List<AccountEntity>) accountRepository.findAll();
        List<AccountModel> accountModels = new ArrayList<AccountModel>();

        for (int i = 0; i < accountEntities.size(); i++) {
            AccountModel accountModel = new AccountModel();
            BeanUtils.copyProperties(accountEntities.get(i), accountModel);
            accountModels.add(accountModel);
        }
        return accountModels;

        // TODO Auto-generated method stub
    }

    // RoleModel
    @Override
    public void addNewRole(RoleModel roleModel) {
        RoleEntity roleEntity = new RoleEntity();

        BeanUtils.copyProperties(roleModel, roleEntity);
        this.roleRepository.save(roleEntity);
        // TODO Auto-generated method stub

    }

    @Override
    public RoleModel findOneByName(String roleName) {
        RoleEntity roleEntity = this.roleRepository.findOneByName(roleName);
        RoleModel roleModel = new RoleModel();

        BeanUtils.copyProperties(roleEntity, roleModel);
        // TODO Auto-generated method stub
        return roleModel;
    }

    @Override
    public List<RoleModel> findAllRoles() {
        List<RoleEntity> roleEntities = (List<RoleEntity>) roleRepository.findAll();
        List<RoleModel> roleModels = new ArrayList<RoleModel>();

        for (int i = 0; i < roleEntities.size(); i++) {
            RoleModel roleModel = new RoleModel();
            BeanUtils.copyProperties(roleEntities.get(i), roleModel);
            roleModels.add(roleModel);
        }
        return roleModels;

        // TODO Auto-generated method stub
    }

    // AcountRoleModel
    @Override
    public void addNewAccountRole(AccountRoleModel accountRoleModel) {
        AccountRoleEntity accountRoleEntity = new AccountRoleEntity();

        BeanUtils.copyProperties(accountRoleModel, accountRoleEntity);

        this.accountRoleRepository.save(accountRoleEntity);
        // TODO Auto-generated method stub

    }

    @Override
    public List<AccountRoleModel> findAllAccountRoles() {
        List<AccountRoleEntity> accountRoleEntities = (List<AccountRoleEntity>) accountRoleRepository.findAll();
        List<AccountRoleModel> accountRoleModels = new ArrayList<AccountRoleModel>();

        for (int i = 0; i < accountRoleEntities.size(); i++) {
            AccountRoleModel accountRoleModel = new AccountRoleModel();
            BeanUtils.copyProperties(accountRoleEntities.get(i), accountRoleModel);
            accountRoleModels.add(accountRoleModel);
        }
        return accountRoleModels;

        // TODO Auto-generated method stub

    }

    @Override
    public void addNewAccountRole(String email, String roleName) {
        AccountEntity accountEntity = accountRepository.findOneByEmail(email);
        RoleEntity roleEntity = roleRepository.findOneByName(roleName);

        this.accountRoleRepository.save(new AccountRoleEntity(accountEntity.getId(), roleEntity.getId()));
        // TODO Auto-generated method stub

    }

    //
    @Override
    public List<AccountInfo> getAccountInfosFromDB() {
        List<AccountRoleEntity> accountRoleEntities = (List<AccountRoleEntity>) accountRoleRepository.findAll();
        List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
        AccountEntity accountEntity = new AccountEntity();
        RoleEntity roleEntity = new RoleEntity();

        int id = 1;
        for(AccountRoleEntity accountRoleEntity : accountRoleEntities){
            accountEntity = accountRepository.findOneById(accountRoleEntity.getAccountId());
            roleEntity = roleRepository.findOneById(accountRoleEntity.getRoleId());

            accountInfos.add(new AccountInfo(id, accountEntity.getEmail(), accountEntity.getEncrytedPassword(), accountEntity.getFullName(), roleEntity.getRoleCode(), roleEntity.getRoleName()));
        }
        return accountInfos;
        // TODO Auto-generated method stub

    }



    
}