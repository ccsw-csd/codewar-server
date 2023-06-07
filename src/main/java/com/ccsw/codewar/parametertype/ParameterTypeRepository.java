package com.ccsw.codewar.parametertype;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccsw.codewar.parametertype.model.ParameterTypeEntity;

public interface ParameterTypeRepository extends JpaRepository<ParameterTypeEntity, Long> {

    List<ParameterTypeEntity> findAll();
}
