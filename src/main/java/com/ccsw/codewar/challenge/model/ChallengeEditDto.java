package com.ccsw.codewar.challenge.model;

import java.util.Date;
import java.util.List;

import com.ccsw.codewar.status.model.StatusDto;
import com.ccsw.codewar.tag.model.TagDto;

public class ChallengeEditDto {
    private Long id;

    private String name;

    private String className;

    private String functionName;

    private Date createdDate;

    private Date endDate;

    private Long tries;

    private StatusDto status;

    private String description;

    private List<ChallengeParameterDto> challengeParameters;

    private List<TagDto> tags;

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

    /**
     * @return the challengeParameters
     */
    public List<ChallengeParameterDto> getChallengeParameters() {
        return challengeParameters;
    }

    /**
     * @param challengeParameters the challengeParameters to set
     */
    public void setChallengeParameters(List<ChallengeParameterDto> challengeParameters) {
        this.challengeParameters = challengeParameters;
    }

    /**
     * @return the tags
     */
    public List<TagDto> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    /**
     * @return the functionName
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * @param functionName the functionName to set
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
}
