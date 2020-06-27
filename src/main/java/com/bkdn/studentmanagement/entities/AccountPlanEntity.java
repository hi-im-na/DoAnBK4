package com.bkdn.studentmanagement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_plan", schema = "personnn")
@AllArgsConstructor
@NoArgsConstructor
public class AccountPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "plan_id")
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

}