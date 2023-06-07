package com.ccsw.codewar.challengeparamenter.model;

import com.ccsw.codewar.challenge.model.Challenge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge_parameter")
public class ChallengeParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parameter_type_id")
    private Long parameterTypeId;

    @Column(name = "order")
    private Long order;

    @Column(name = "is_input")
    private Boolean isInput;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

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

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
