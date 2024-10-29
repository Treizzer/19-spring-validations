package com.treizer.spring_validations.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.treizer.spring_validations.persistence.entity.EmployeeEntity;

@Repository
public interface IEmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

}
