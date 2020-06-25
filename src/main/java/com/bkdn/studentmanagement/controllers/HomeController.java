package com.bkdn.studentmanagement.controllers;

import java.lang.ProcessBuilder.Redirect;

import com.bkdn.studentmanagement.configs.models.structures.AccountInfo;
import com.bkdn.studentmanagement.entities.AccountEntity;
import com.bkdn.studentmanagement.entities.AccountRoleEntity;
import com.bkdn.studentmanagement.entities.RoleEntity;
import com.bkdn.studentmanagement.repositories.AccountRepository;
import com.bkdn.studentmanagement.repositories.AccountRoleRepository;
import com.bkdn.studentmanagement.repositories.RoleRepository;
import com.bkdn.studentmanagement.services.AccountRoleService;
import com.bkdn.studentmanagement.services.AccountService;
import com.bkdn.studentmanagement.services.RoleService;
import com.bkdn.studentmanagement.utils.EncrytedPasswordUtils;
import com.bkdn.studentmanagement.models.AccountModel;
import com.bkdn.studentmanagement.models.AccountRoleModel;
import com.bkdn.studentmanagement.models.RoleModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Autowired
    RoleService roleService;

    @Autowired
    AccountRoleService accountRoleService;

    @GetMapping("/403")
    public String error() {
        return "error/403";
    }

    @GetMapping("/register")
    public String register() {
        return "layouts/admin/pages/register";
    }


    @PostMapping(value = "/register")
    public String tt(@RequestParam("regEmail") String email, @RequestParam("regPassword") String pass,
            @RequestParam("regName") String fullName, @RequestParam("regRole") String role) {

        // System.out.println("**********" + email + " " + pass + "**********");
        // Kiem tra trung
        
        AccountModel accountModelCheck = this.accountService.findOneByEmail(email);
        if (accountModelCheck != null) {
            return "redirect:/register?error=true";
        }
        return "redirect:/confirm";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("regEmail") String email,
    @RequestParam("regName") String fullName, @RequestParam("regRole") String role, Model m) {
        m.addAttribute("obj_email", email);
        m.addAttribute("obj_name", fullName);
        m.addAttribute("obj_role", role);
        return "layouts/admin/pages/confirm";
    }

    @PostMapping("/confirm")
    public String confirmPost(@RequestParam("regEmail") String email, @RequestParam("regPassword") String pass,
            @RequestParam("regName") String fullName, @RequestParam("regRole") String role) {
        
        accountService.addNewAccount(new AccountModel(email,EncrytedPasswordUtils.encrytedPassword(pass),fullName));
        AccountModel accountModel = accountService.findOneByEmail(email);
        RoleModel roleModel = roleService.findOneByName(role);
        
        accountRoleService.addNewAccountRole(new AccountRoleModel(accountModel.getId(), roleModel.getId()));
        return "redirect:/accounts";
    }
}
