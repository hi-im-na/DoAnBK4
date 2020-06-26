package com.bkdn.studentmanagement.repositories;

import com.bkdn.studentmanagement.entities.LocationEntity;

import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<LocationEntity, Integer>{
    
}