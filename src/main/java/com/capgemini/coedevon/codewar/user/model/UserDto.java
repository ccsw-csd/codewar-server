package com.capgemini.coedevon.codewar.user.model;

import java.io.Serializable;

public class UserDto implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String role;

  private String displayName;

  private String firstName;

  private String lastName;

  private String mail;

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
   * @return username
   */
  public String getUsername() {

    return this.username;
  }

  /**
   * @param username new value of {@link #getusername}.
   */
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return role
   */
  public String getRole() {

    return this.role;
  }

  /**
   * @param role new value of {@link #getrole}.
   */
  public void setRole(String role) {

    this.role = role;
  }

  /**
   * @return displayName
   */
  public String getDisplayName() {

    return this.displayName;
  }

  /**
   * @param displayName new value of {@link #getdisplayName}.
   */
  public void setDisplayName(String displayName) {

    this.displayName = displayName;
  }

  /**
   * @return firstName
   */
  public String getFirstName() {

    return this.firstName;
  }

  /**
   * @param firstName new value of {@link #getfirstName}.
   */
  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  /**
   * @return lastName
   */
  public String getLastName() {

    return this.lastName;
  }

  /**
   * @param lastName new value of {@link #getlastName}.
   */
  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  /**
   * @return mail
   */
  public String getMail() {

    return this.mail;
  }

  /**
   * @param mail new value of {@link #getmail}.
   */
  public void setMail(String mail) {

    this.mail = mail;
  }

}
