package com.ccsw.codewar.challenge.model;

import java.util.Date;
import java.util.List;

import com.ccsw.codewar.status.model.StatusDto;

public class ChallengeEditDto {
    private Long id;

    private String name;

    private Date createdDate;

    private Date endDate;

    private Long tries;

    private StatusDto status;

    private String description;

    private List<ChallengeParameterDto> challengeParameter;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getTries() {
        return this.tries;
    }

    public void setTries(Long tries) {
        this.tries = tries;
    }

    public List<ChallengeParameterDto> getChallengeParameter() {
        return challengeParameter;
    }

    public void setChallengeParameter(List<ChallengeParameterDto> challengeParameter) {
        this.challengeParameter = challengeParameter;
    }

    /**
     * @return the status
     */
    public StatusDto getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
