package com.ccsw.codewar.parametertype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.codewar.parametertype.model.ParameterType;

@Service
public class ParameterTypeServiceImpl implements ParameterTypeService{

    @Autowired
    private ParameterTypeRepository parameterTypeRepository;

    @Override
    public List<ParameterType> findAll() {
        return parameterTypeRepository.findAll();
    }
}
