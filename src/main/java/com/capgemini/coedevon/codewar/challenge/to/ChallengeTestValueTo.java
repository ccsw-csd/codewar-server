package com.capgemini.coedevon.codewar.challenge.to;

import java.io.Serializable;

public class ChallengeTestValueTo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private ChallengeParameterTo parameter;

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
   * @return parameter
   */
  public ChallengeParameterTo getParameter() {

    return this.parameter;
  }

  /**
   * @param parameter new value of {@link #getparameter}.
   */
  public void setParameter(ChallengeParameterTo parameter) {

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
