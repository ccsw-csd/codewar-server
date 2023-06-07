package com.ccsw.codewar.parametertype;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.codewar.parametertype.model.ParameterTypeDto;
import com.ccsw.codewar.parametertype.model.ParameterTypeEntity;

@RequestMapping(value = "/parameter_type")
@RestController
public class ParameterTypeController {

    @Autowired
    private ParameterTypeService parameterTypeService;

    @Autowired
    private DozerBeanMapper mapper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ParameterTypeDto> findAll() {

        List<ParameterTypeEntity> parameter = this.parameterTypeService.findAll();

        return parameter.stream().map(e -> mapper.map(e, ParameterTypeDto.class)).collect(Collectors.toList());
    }
}
