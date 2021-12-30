package com.capgemini.ccsw.codewar.master.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.master.model.ParameterTypeEntity;

public interface ParameterTypeRepository extends CrudRepository<ParameterTypeEntity, Long> {

   Optional<ParameterTypeEntity> getByCode(String code);

}