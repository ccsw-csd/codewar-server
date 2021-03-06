package com.ccsw.codewar.user.model;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {
   /**
   *
   */
   private static final long serialVersionUID = 1L;

   private Long id;

   private String username;

   private String role;

   private String firstName;

   private String lastName;

   private String mail;

   private Date dateCreation;

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
