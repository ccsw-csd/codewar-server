package com.capgemini.ccsw.codewar.challenge.to;

import java.io.Serializable;
import java.util.List;

public class ChallengeTestTo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private Integer order;

  private Integer maxTime;

  private Boolean visible;

  private Boolean performance;

  private List<ChallengeTestValueTo> values;

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
   * @return order
   */
  public Integer getOrder() {

    return this.order;
  }

  /**
   * @param order new value of {@link #getorder}.
   */
  public void setOrder(Integer order) {

    this.order = order;
  }

  /**
   * @return maxTime
   */
  public Integer getMaxTime() {

    return this.maxTime;
  }

  /**
   * @param maxTime new value of {@link #getmaxTime}.
   */
  public void setMaxTime(Integer maxTime) {

    this.maxTime = maxTime;
  }

  /**
   * @return visible
   */
  public Boolean getVisible() {

    return this.visible;
  }

  /**
   * @param visible new value of {@link #getvisible}.
   */
  public void setVisible(Boolean visible) {

    this.visible = visible;
  }

  /**
   * @return performance
   */
  public Boolean getPerformance() {

    return this.performance;
  }

  /**
   * @param performance new value of {@link #getperformance}.
   */
  public void setPerformance(Boolean performance) {

    this.performance = performance;
  }

  /**
   * @return values
   */
  public List<ChallengeTestValueTo> getValues() {

    return values;
  }

  /**
   * @param values new value of {@link #getvalues}.
   */
  public void setValues(List<ChallengeTestValueTo> values) {

    this.values = values;
  }

}
