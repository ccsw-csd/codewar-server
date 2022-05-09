package com.ccsw.codewar.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "codewar", name = "challenge_test")
public class ChallengeTestEntity {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "challenge_id")
   private ChallengeEntity challenge;

   @Column(name = "name")
   private String name;

   @Column(name = "`order`")
   private Integer order;

   @Column(name = "max_time")
   private Integer maxTime;

   @Column(name = "is_visible")
   private Boolean visible;

   @Column(name = "is_performance")
   private Boolean performance;

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

}
