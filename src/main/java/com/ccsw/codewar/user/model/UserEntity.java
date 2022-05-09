package com.ccsw.codewar.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(schema = "codewar", name = "user")
public class UserEntity {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "username", unique = true)
  private String username;

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "role", referencedColumnName = "id", insertable = true, updatable = true)
  private RoleEntity role;

  @NotNull
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @NotNull
  @Column(name = "email", unique = true)
  private String mail;

  @NotNull
  @Column(name = "date_creation")
  private Date dateCreation;

  /**
   * @return the id
   */
  public Long getId() {

    return this.id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @return the username
   */
  public String getUsername() {

    return this.username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return the role
   */
  public RoleEntity getRole() {

    return this.role;
  }

  /**
   * @param role the role to set
   */
  public void setRole(RoleEntity role) {

    this.role = role;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {

    return this.firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {

    return this.lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  /**
   * @return the mail
   */
  public String getMail() {

    return this.mail;
  }

  /**
   * @param mail the mail to set
   */
  public void setMail(String mail) {

    this.mail = mail;
  }

  /**
   * @return dateCreation
   */
  public Date getDateCreation() {

    return this.dateCreation;
  }

  /**
   * @param dateCreation new value of {@link #getdateCreation}.
   */
  public void setDateCreation(Date dateCreation) {

    this.dateCreation = dateCreation;
  }
}
