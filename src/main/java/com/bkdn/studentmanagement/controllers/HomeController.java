package com.bkdn.studentmanagement.controllers;

import java.lang.ProcessBuilder.Redirect;

import com.bkdn.studentmanagement.configs.models.structures.AccountInfo;
import com.bkdn.studentmanagement.entities.AccountEntity;
import com.bkdn.studentmanagement.entities.AccountRoleEntity;
import com.bkdn.studentmanagement.entities.RoleEntity;
import com.bkdn.studentmanagement.repositories.AccountRepository;
import com.bkdn.studentmanagement.repositories.AccountRoleRepository;
import com.bkdn.studentmanagement.repositories.RoleRepository;
import com.bkdn.studentmanagement.utils.EncrytedPasswordUtils;

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
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRoleRepository accountRoleRepository;

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
        
        AccountEntity accountEntityCheck = accountRepository.findOneByEmail(email);
        if (accountEntityCheck != null) {
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
        accountRepository.save(new AccountEntity(email, EncrytedPasswordUtils.encrytedPassword(pass), fullName));
        RoleEntity roleEntity = roleRepository.findOneByName(role);
        AccountEntity accountEntity = accountRepository.findOneByEmail(email);
        accountRoleRepository.save(new AccountRoleEntity(accountEntity.getId(), roleEntity.getId()));
        return "redirect:/accounts";
    }
}
