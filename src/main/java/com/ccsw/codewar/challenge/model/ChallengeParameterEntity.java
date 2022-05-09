package com.ccsw.codewar.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.codewar.master.model.ParameterTypeEntity;

@Entity
@Table(schema = "codewar", name = "challenge_parameter")
public class ChallengeParameterEntity {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "challenge_id")
   private ChallengeEntity challenge;

   @ManyToOne
   @JoinColumn(name = "parameter_type_id")
   private ParameterTypeEntity type;

   @Column(name = "name")
   private String name;

   @Column(name = "`order`")
   private Integer order;

   @Column(name = "is_input")
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
   * @return type
   */
   public ParameterTypeEntity getType() {

      return this.type;
   }

   /**
   * @param type new value of {@link #gettype}.
   */
   public void setType(ParameterTypeEntity type) {

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
