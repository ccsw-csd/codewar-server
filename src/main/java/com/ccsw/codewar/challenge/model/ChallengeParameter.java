package com.ccsw.codewar.challenge.model;

import com.ccsw.codewar.parametertype.model.ParameterType;

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
public class ChallengeParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parameter_type_id", nullable = false)
    private ParameterType parameterType;

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

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    /**
     * @return the parameterType
     */
    public ParameterType getParameterType() {
        return parameterType;
    }

    /**
     * @param parameterType the parameterType to set
     */
    public void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
    }

}
