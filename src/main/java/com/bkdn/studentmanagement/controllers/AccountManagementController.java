package com.bkdn.studentmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import com.bkdn.studentmanagement.configs.models.structures.AccountInfo;
import com.bkdn.studentmanagement.configs.services.AccountService;
import com.bkdn.studentmanagement.entities.AccountEntity;
import com.bkdn.studentmanagement.entities.AccountRoleEntity;
import com.bkdn.studentmanagement.entities.RoleEntity;
import com.bkdn.studentmanagement.repositories.AccountRepository;
import com.bkdn.studentmanagement.repositories.AccountRoleRepository;
import com.bkdn.studentmanagement.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class AccountManagementController {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRoleRepository accountRoleRepository;

    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/accounts")
    public String accounts(Model m) {
        List<AccountRoleEntity> accountRoleEntities = (List<AccountRoleEntity>) accountRoleRepository.findAll();
        List<RoleEntity> roleEntities = (List<RoleEntity>) roleRepository.findAll();
        List<AccountEntity> accountEntities = (List<AccountEntity>) accountRepository.findAll();

        List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();

        RoleEntity roleEntity = new RoleEntity();
        AccountEntity accountEntity = new AccountEntity();

        int id = 1;

        for (AccountRoleEntity accountRoleEntity : accountRoleEntities) {
            roleEntity = _getRoleByRoleId(roleEntities, accountRoleEntity.getRoleId());
            accountEntity = _getAccountByAccountId(accountEntities, accountRoleEntity.getAccountId());

            accountInfos.add(new AccountInfo(id, accountEntity.getEmail(), accountEntity.getEncrytedPassword(),
                    accountEntity.getFullName(), roleEntity.getRoleCode(), roleEntity.getRoleName()));
            id++;
        }

        m.addAttribute("accountInfos", accountInfos);
        return "layouts/admin/pages/accounts";
    }



    private RoleEntity _getRoleByRoleId(List<RoleEntity> roleEntities, int roleId) {
        for (RoleEntity roleEntity : roleEntities) {
            if (roleEntity.getId().intValue() == roleId) {
                return roleEntity;
            }
        }
        return null;
    }

    private AccountEntity _getAccountByAccountId(List<AccountEntity> accountEntities, int accountId) {
        for (AccountEntity accountEntity : accountEntities) {
            if (accountEntity.getId().intValue() == accountId)
                return accountEntity;
        }
        return null;
    }
}