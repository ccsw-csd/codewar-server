package com.capgemini.ccsw.codewar.participation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeEntity;
import com.capgemini.ccsw.codewar.user.model.UserEntity;

@Entity
@Table(schema = "codewar", name = "participation")
public class ParticipationEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private UserEntity user;

   @ManyToOne
   @JoinColumn(name = "challenge_id")
   private ChallengeEntity challenge;

   @Column(name = "date")
   private Date date;

   @Column(name = "evaluated")
   private Boolean evaluated;

   /**
    * @return the evaluated
    */
   public Boolean getEvaluated() {
      return evaluated;
   }

   /**
    * @param evaluated the evaluated to set
    */
   public void setEvaluated(Boolean evaluated) {
      this.evaluated = evaluated;
   }

   /**
    * @return the date
    */
   public Date getDate() {
      return date;
   }

   /**
    * @param date the date to set
    */
   public void setDate(Date date) {
      this.date = date;
   }

   /**
    * @return the code
    */
   public String getCode() {
      return code;
   }

   /**
    * @param code the code to set
    */
   public void setCode(String code) {
      this.code = code;
   }

   @Column(name = "code")
   private String code;

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
   public UserEntity getUser() {

      return this.user;
   }

   /**
   * @param user new value of {@link #getuser}.
   */
   public void setUser(UserEntity user) {

      this.user = user;
   }

   /**
   * @return challenge
   */
   public ChallengeEntity getChallenge() {

      return this.challenge;
   }

   /**
   * @param challenge new value of {@link #getchallenge}.
   */
   public void setChallenge(ChallengeEntity challenge) {

      this.challenge = challenge;
   }

}
