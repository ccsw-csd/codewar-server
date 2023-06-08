package com.ccsw.codewar.challengeparamenter.model;

import com.ccsw.codewar.parametertype.model.ParameterTypeDto;

public class ChallengeParameterDto {

    private Long id;

    private String name;

    private ParameterTypeDto parameterType;

    private Long order;

    private Boolean isInput;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOrder() {
        return order;
    }

    public Boolean getIsInput() {
        return isInput;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public void setIsInput(Boolean isInput) {
        this.isInput = isInput;
    }

    public ParameterTypeDto getParameterType() {
        return parameterType;
    }

    public void setParameterType(ParameterTypeDto parameterType) {
        this.parameterType = parameterType;
    }
}
