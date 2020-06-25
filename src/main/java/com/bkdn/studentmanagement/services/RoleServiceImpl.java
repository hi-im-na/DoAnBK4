package com.bkdn.studentmanagement.services;

import com.bkdn.studentmanagement.entities.RoleEntity;
import com.bkdn.studentmanagement.models.RoleModel;
import com.bkdn.studentmanagement.repositories.RoleRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addNewRole(RoleModel roleModel) {
        RoleEntity roleEntity = new RoleEntity();

        BeanUtils.copyProperties(roleModel, roleEntity);
        this.roleRepository.save(roleEntity);
        // TODO Auto-generated method stub

    }

    @Override
    public RoleModel findOneByName(String roleName) {
        RoleEntity roleEntity = this.roleRepository.findOneByName(roleName);
        RoleModel roleModel = new RoleModel();
        
        BeanUtils.copyProperties(roleEntity, roleModel);
        // TODO Auto-generated method stub
        return roleModel;
    }
}