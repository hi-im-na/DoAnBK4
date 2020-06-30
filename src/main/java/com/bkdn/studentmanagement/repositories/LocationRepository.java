package com.bkdn.studentmanagement.repositories;

import com.bkdn.studentmanagement.entities.LocationEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<LocationEntity, Integer>{
    
    @Query(value = "Select * from location l where l.id = ?1", nativeQuery = true)
    public LocationEntity  overrideFindById(Integer id);
}