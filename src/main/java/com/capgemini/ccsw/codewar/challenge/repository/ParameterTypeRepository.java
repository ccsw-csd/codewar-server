package com.capgemini.ccsw.codewar.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.challenge.model.ParameterTypeEntity;

public interface ParameterTypeRepository extends CrudRepository<ParameterTypeEntity, Long> {

   ParameterTypeEntity getByCode(String code);

}