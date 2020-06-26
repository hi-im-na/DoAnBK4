package com.bkdn.studentmanagement.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountModel {
    private Integer id;
    private String email;
    private String encrytedPassword;
    private String fullName;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public AccountModel(String email, String encryted_password, String fullName){
        this.email = email;
        this.encrytedPassword = encryted_password;
        this.fullName = fullName;
    }
}