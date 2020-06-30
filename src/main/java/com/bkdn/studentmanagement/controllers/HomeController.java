package com.bkdn.studentmanagement.controllers;

import java.util.List;

import com.bkdn.studentmanagement.configs.models.structures.AccountInfo;
import com.bkdn.studentmanagement.services.AccountService;
import com.bkdn.studentmanagement.utils.EncrytedPasswordUtils;
import com.bkdn.studentmanagement.models.AccountModel;
import com.bkdn.studentmanagement.models.RoleModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping(value = "")
    public String index(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/sign-in";
        }
    }

    @Autowired
    AccountService accountService;

    @GetMapping("/register")
    public String register(Model m) {
        List<RoleModel> roleModels = accountService.findAllRoles();
        m.addAttribute("accountInfos", roleModels);
        return "layouts/admin/pages/register";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("regEmail") String email, @RequestParam("regPassword") String pass,
            @RequestParam("regName") String fullName, @RequestParam("regRole") String role, Model m) {
        AccountModel accountModelCheck = this.accountService.findOneByEmail(email);
        if (accountModelCheck != null) {
            return "redirect:/register?error=true";
        }
        m.addAttribute("accountInfo", new AccountInfo(email, fullName, role));
        return "layouts/admin/pages/confirm";
    }

    @PostMapping("/confirm")
    public String confirmPost(@RequestParam("regEmail") String email, @RequestParam("regPassword") String pass,
            @RequestParam("regName") String fullName, @RequestParam("regRole") String role) {
        accountService.addNewAccount(new AccountModel(email, EncrytedPasswordUtils.encrytedPassword(pass), fullName));
        accountService.addNewAccountRole(email, role);
        return "redirect:/accounts";
    }
}
