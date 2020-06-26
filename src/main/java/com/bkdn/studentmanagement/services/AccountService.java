package com.bkdn.studentmanagement.services;

import java.util.List;

import com.bkdn.studentmanagement.models.AccountModel;
import com.bkdn.studentmanagement.models.AccountRoleModel;
import com.bkdn.studentmanagement.models.RoleModel;

public interface AccountService {

    //Account
    public void addNewAccount(AccountModel model);
    public AccountModel findOneByEmail(String email);
    public List<AccountModel> findAllAccounts(); 
    
    //Role
    public void addNewRole(RoleModel roleModel);
    public RoleModel findOneByName(String roleName);
    public List<RoleModel> findAllRoles();
    
    //Account_Role
    public void addNewAccountRole(AccountRoleModel accountRoleModel);
    public List<AccountRoleModel> findAllAccountRoles();
    

}