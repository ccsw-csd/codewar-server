package com.ccsw.codewar.challenge.model;

import java.util.Date;
import java.util.List;

import com.ccsw.codewar.person.model.PersonDto;
import com.ccsw.codewar.status.model.StatusDto;
import com.ccsw.codewar.tag.model.TagDto;

public class ChallengeItemListDto {

    private Long id;

    private String name;

    private Date createdDate;

    private Date endDate;

    private Long tries;

    private StatusDto status;

    private PersonDto person;

    private Long distinctUsername;

    private List<TagDto> tags;

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

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public Long getDistinctUsername() {
        return this.distinctUsername;
    }

    public void setDistinctUsername(Long distinctUsername) {
        this.distinctUsername = distinctUsername;
    }

}
