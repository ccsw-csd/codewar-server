package com.capgemini.coedevon.codewar.participation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.coedevon.codewar.challenge.model.ChallengeEntity;
import com.capgemini.coedevon.codewar.user.model.UserEntity;

@Entity
@Table(schema = "codewar", name = "participation")
public class ParticipationEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "challenge_id")
  private ChallengeEntity challenge;

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
