package com.bkdn.studentmanagement.services;

import com.bkdn.studentmanagement.models.RoleModel;

public interface RoleService {
    public void addNewRole(RoleModel roleModel);
    public RoleModel findOneByName(String roleName);
}