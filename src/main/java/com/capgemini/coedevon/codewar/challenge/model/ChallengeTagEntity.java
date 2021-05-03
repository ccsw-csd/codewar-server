package com.capgemini.coedevon.codewar.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "codewar", name = "challenge_tag")
public class ChallengeTagEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "challenge_id")
  private ChallengeEntity challenge;

  @ManyToOne
  @JoinColumn(name = "tag_id")
  private TagEntity tag;

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

  /**
   * @return tag
   */
  public TagEntity getTag() {

    return this.tag;
  }

  /**
   * @param tag new value of {@link #gettag}.
   */
  public void setTag(TagEntity tag) {

    this.tag = tag;
  }

}
