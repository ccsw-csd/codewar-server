package com.ccsw.codewar.parametertype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.codewar.parametertype.model.ParameterTypeEntity;

@Service
public class ParameterTypeServiceImpl implements ParameterTypeService{

    @Autowired
    private ParameterTypeRepository parameterTypeRepository;

    @Override
    public List<ParameterTypeEntity> findAll() {
        return parameterTypeRepository.findAll();
    }
}
