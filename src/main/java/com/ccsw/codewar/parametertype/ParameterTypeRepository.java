package com.ccsw.codewar.parametertype;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccsw.codewar.parametertype.model.ParameterType;

public interface ParameterTypeRepository extends JpaRepository<ParameterType, Long> {

    List<ParameterType> findAll();
}
