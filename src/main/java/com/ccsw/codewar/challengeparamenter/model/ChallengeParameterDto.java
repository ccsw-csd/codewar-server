package com.ccsw.codewar.challengeparamenter.model;

public class ChallengeParameterDto {

    private Long id;

    private String name;

    private Long parameterTypeId;

    private Long order;

    private Boolean isInput;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParameterTypeId() {
        return parameterTypeId;
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

    public void setParameterTypeId(Long parameterTypeId) {
        this.parameterTypeId = parameterTypeId;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public void setIsInput(Boolean isInput) {
        this.isInput = isInput;
    }
}
