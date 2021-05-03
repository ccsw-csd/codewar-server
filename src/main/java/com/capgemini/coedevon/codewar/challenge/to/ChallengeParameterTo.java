package com.capgemini.coedevon.codewar.challenge.to;

import java.io.Serializable;

public class ChallengeParameterTo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private ParameterTypeTo type;

  private String name;

  private Integer order;

  private Boolean input;

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
   * @return type
   */
  public ParameterTypeTo getType() {

    return this.type;
  }

  /**
   * @param type new value of {@link #gettype}.
   */
  public void setType(ParameterTypeTo type) {

    this.type = type;
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
   * @return input
   */
  public Boolean getInput() {

    return this.input;
  }

  /**
   * @param input new value of {@link #getinput}.
   */
  public void setInput(Boolean input) {

    this.input = input;
  }

}
