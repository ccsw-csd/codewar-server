package com.ccsw.codewar.challenge.model;

import java.util.Date;
import java.util.List;

import com.ccsw.codewar.challengeparamenter.model.ChallengeParameterEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "tries", nullable = false)
    private Long tries;

    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "challenge")
    private List<ChallengeParameterEntity> challengeParameter;

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

    public Long getStatusId() {
        return this.statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ChallengeParameterEntity> getChallengeParameter() {
        return challengeParameter;
    }

    public void setChallengeParameter(List<ChallengeParameterEntity> challengeParameter) {
        this.challengeParameter = challengeParameter;
    }
}
