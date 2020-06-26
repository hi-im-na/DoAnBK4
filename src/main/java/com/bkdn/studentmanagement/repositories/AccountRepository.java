package com.bkdn.studentmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bkdn.studentmanagement.entities.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
    @Query(value = "Select * from account a where a.email = ?1", nativeQuery = true)
    public AccountEntity findOneByEmail(String email);

    @Query(value = "Select * from account a where a.id = ?1", nativeQuery = true)
    public AccountEntity findOneById(Integer id);
}
