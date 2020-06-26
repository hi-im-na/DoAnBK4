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

        
        List<AccountInfo> accountInfos = accountService.getAccountInfosFromDB();

        m.addAttribute("accountInfos", accountInfos);
        return "layouts/admin/pages/accounts";
    }


}