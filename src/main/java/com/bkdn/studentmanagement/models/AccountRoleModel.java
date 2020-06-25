package com.bkdn.studentmanagement.models;

public class AccountRoleModel {
    private Integer id;
    private Integer accountId;
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public AccountRoleModel(Integer accountId, Integer roleId){
        this.accountId = accountId;
        this.roleId = roleId;
    }
    
}