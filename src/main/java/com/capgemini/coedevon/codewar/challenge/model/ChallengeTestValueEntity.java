package com.capgemini.coedevon.codewar.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "codewar", name = "challenge_test_value")
public class ChallengeTestValueEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "challenge_test_id")
  private ChallengeTestEntity test;

  @ManyToOne
  @JoinColumn(name = "challenge_parameter_id")
  private ChallengeParameterEntity parameter;

  @Column(name = "value")
  private String value;

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
   * @return test
   */
  public ChallengeTestEntity getTest() {

    return this.test;
  }

  /**
   * @param test new value of {@link #gettest}.
   */
  public void setTest(ChallengeTestEntity test) {

    this.test = test;
  }

  /**
   * @return parameter
   */
  public ChallengeParameterEntity getParameter() {

    return this.parameter;
  }

  /**
   * @param parameter new value of {@link #getparameter}.
   */
  public void setParameter(ChallengeParameterEntity parameter) {

    this.parameter = parameter;
  }

  /**
   * @return value
   */
  public String getValue() {

    return this.value;
  }

  /**
   * @param value new value of {@link #getvalue}.
   */
  public void setValue(String value) {

    this.value = value;
  }

}
