package com.ccsw.codewar.challenge.model;

import java.util.Date;
import java.util.List;

import com.ccsw.codewar.status.model.Status;
import com.ccsw.codewar.tag.model.Tag;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "challenge")
    private List<ChallengeParameter> challengeParameters;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "challenge_tag", //
            joinColumns = { @JoinColumn(name = "challenge_id", referencedColumnName = "id") }, //
            inverseJoinColumns = { @JoinColumn(name = "tag_id", referencedColumnName = "id") })
    private List<Tag> tags;

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
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the challengeParameters
     */
    public List<ChallengeParameter> getChallengeParameters() {
        return challengeParameters;
    }

    /**
     * @param challengeParameters the challengeParameters to set
     */
    public void setChallengeParameters(List<ChallengeParameter> challengeParameters) {
        this.challengeParameters = challengeParameters;
    }

    /**
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
