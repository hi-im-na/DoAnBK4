package com.bkdn.studentmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import com.bkdn.studentmanagement.configs.models.structures.AccountInfo;

import com.bkdn.studentmanagement.models.AccountModel;
import com.bkdn.studentmanagement.models.AccountRoleModel;
import com.bkdn.studentmanagement.models.RoleModel;


import com.bkdn.studentmanagement.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AccountManagementController {


    @Autowired
    AccountService accountService;

    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/accounts")
    public String accounts(Model m) {

        
        List<AccountRoleModel> accountRoleModels = (List<AccountRoleModel>) accountService.findAllAccountRoles();
        List<RoleModel> roleModels = (List<RoleModel>) accountService.findAllRoles();
        List<AccountModel> accountModels = (List<AccountModel>) accountService.findAllAccounts();

        List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();

        RoleModel roleModel = new RoleModel();
        AccountModel accountModel = new AccountModel();

        int id = 1;

        for (AccountRoleModel accountRoleModel : accountRoleModels) {
            roleModel = _getRoleByRoleId(roleModels, accountRoleModel.getRoleId());
            accountModel = _getAccountByAccountId(accountModels, accountRoleModel.getAccountId());

            accountInfos.add(new AccountInfo(id, accountModel.getEmail(), accountModel.getEncrytedPassword(),
                    accountModel.getFullName(), roleModel.getRoleCode(), roleModel.getRoleName()));
            id++;
        }

        m.addAttribute("accountInfos", accountInfos);
        return "layouts/admin/pages/accounts";
    }



    private RoleModel _getRoleByRoleId(List<RoleModel> roleModels, int roleId) {
        for (RoleModel roleModel : roleModels) {
            if (roleModel.getId().intValue() == roleId) {
                return roleModel;
            }
        }
        return null;
    }

    private AccountModel _getAccountByAccountId(List<AccountModel> accountModels, int accountId) {
        for (AccountModel accountModel : accountModels) {
            if (accountModel.getId().intValue() == accountId)
                return accountModel;
        }
        return null;
    }
}