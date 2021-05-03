package com.capgemini.coedevon.codewar.challenge.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.capgemini.coedevon.codewar.user.model.UserDto;

public class ChallengeTo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private UserDto user;

  private ChallengeStatusTo status;

  private Date creationDate;

  private String name;

  private String description;

  private String baseCode;

  private String functionName;

  private String className;

  private Date startDate;

  private Date endDate;

  private Boolean multipleTries;

  private List<ChallengeParameterTo> parameters;

  private List<TagTo> tags;

  private List<ChallengeTestTo> tests;

  private Long numParticipation;

  /**
   * @return id
   */
  public Long getId() {

    return this.id;
  }

  /**
   * @param id new value of {@link #getid}.
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @return user
   */
  public UserDto getUser() {

    return this.user;
  }

  /**
   * @param user new value of {@link #getuser}.
   */
  public void setUser(UserDto user) {

    this.user = user;
  }

  /**
   * @return status
   */
  public ChallengeStatusTo getStatus() {

    return this.status;
  }

  /**
   * @param status new value of {@link #getstatus}.
   */
  public void setStatus(ChallengeStatusTo status) {

    this.status = status;
  }

  /**
   * @return creationDate
   */
  public Date getCreationDate() {

    return this.creationDate;
  }

  /**
   * @param creationDate new value of {@link #getcreationDate}.
   */
  public void setCreationDate(Date creationDate) {

    this.creationDate = creationDate;
  }

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return description
   */
  public String getDescription() {

    return this.description;
  }

  /**
   * @param description new value of {@link #getdescription}.
   */
  public void setDescription(String description) {

    this.description = description;
  }

  /**
   * @return baseCode
   */
  public String getBaseCode() {

    return this.baseCode;
  }

  /**
   * @param baseCode new value of {@link #getbaseCode}.
   */
  public void setBaseCode(String baseCode) {

    this.baseCode = baseCode;
  }

  /**
   * @return functionName
   */
  public String getFunctionName() {

    return this.functionName;
  }

  /**
   * @param functionName new value of {@link #getfunctionName}.
   */
  public void setFunctionName(String functionName) {

    this.functionName = functionName;
  }

  /**
   * @return className
   */
  public String getClassName() {

    return this.className;
  }

  /**
   * @param className new value of {@link #getclassName}.
   */
  public void setClassName(String className) {

    this.className = className;
  }

  /**
   * @return startDate
   */
  public Date getStartDate() {

    return this.startDate;
  }

  /**
   * @param startDate new value of {@link #getstartDate}.
   */
  public void setStartDate(Date startDate) {

    this.startDate = startDate;
  }

  /**
   * @return endDate
   */
  public Date getEndDate() {

    return this.endDate;
  }

  /**
   * @param endDate new value of {@link #getendDate}.
   */
  public void setEndDate(Date endDate) {

    this.endDate = endDate;
  }

  /**
   * @return multipleTries
   */
  public Boolean getMultipleTries() {

    return this.multipleTries;
  }

  /**
   * @param multipleTries new value of {@link #getmultipleTries}.
   */
  public void setMultipleTries(Boolean multipleTries) {

    this.multipleTries = multipleTries;
  }

  /**
   * @return parameters
   */
  public List<ChallengeParameterTo> getParameters() {

    return this.parameters;
  }

  /**
   * @param parameters new value of {@link #getparameters}.
   */
  public void setParameters(List<ChallengeParameterTo> parameters) {

    this.parameters = parameters;
  }

  /**
   * @return tags
   */
  public List<TagTo> getTags() {

    return this.tags;
  }

  /**
   * @param tags new value of {@link #gettags}.
   */
  public void setTags(List<TagTo> tags) {

    this.tags = tags;
  }

  /**
   * @return tests
   */
  public List<ChallengeTestTo> getTests() {

    return this.tests;
  }

  /**
   * @param tests new value of {@link #gettests}.
   */
  public void setTests(List<ChallengeTestTo> tests) {

    this.tests = tests;
  }

  /**
   * @return numParticipation
   */
  public Long getNumParticipation() {

    return this.numParticipation;
  }

  /**
   * @param numParticipation new value of {@link #getnumParticipation}.
   */
  public void setNumParticipation(Long numParticipation) {

    this.numParticipation = numParticipation;
  }

  public ChallengeTo() {

    super();
  }

  /**
   * The constructor.
   * @param id
   * @param userId
   * @param statusId
   * @param statusName
   * @param statusCode
   * @param name
   * @param startDate
   * @param endDate
   * @param multipleTries
   * @param numParticipation
   */
  public ChallengeTo(Long id, Long userId, Long statusId, String statusName, String statusCode, String name,
      Date creationDate, Date startDate, Date endDate, Boolean multipleTries, Long numParticipation) {

    super();
    this.id = id;

    this.user = new UserDto();
    this.user.setId(userId);

    this.status = new ChallengeStatusTo();
    this.status.setId(statusId);
    this.status.setName(statusName);
    this.status.setCode(statusCode);

    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.creationDate = creationDate;
    this.multipleTries = multipleTries;
    this.numParticipation = numParticipation;
  }

}
