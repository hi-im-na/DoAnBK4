package com.bkdn.studentmanagement.models;

public class AccountPlanModel {
    private Integer id;
    private Integer accountId;
    private Integer planId;


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

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public AccountPlanModel(Integer accountId, Integer planId){
        this.accountId = accountId;
        this.planId = planId;
    }
}