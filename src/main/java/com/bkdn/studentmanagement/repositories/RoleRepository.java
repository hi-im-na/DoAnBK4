package com.bkdn.studentmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bkdn.studentmanagement.entities.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    public RoleEntity findOneById(int id);

    @Query(value = "Select * from role r where r.role_name = ?1",nativeQuery = true)
	public RoleEntity findOneByName(String role);
}
