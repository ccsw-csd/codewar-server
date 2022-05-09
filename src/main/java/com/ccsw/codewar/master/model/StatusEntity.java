package com.ccsw.codewar.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "codewar", name = "challenge_status")
public class StatusEntity {

   public static final String BORRADOR = "PND";
   public static final String ACTIVADO = "ACT";
   public static final String CERRADO = "CLO";

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "id")
   private Long id;

   @Column(name = "code")
   private String code;

   @Column(name = "name")
   private String name;

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
   * @return code
   */
   public String getCode() {

      return this.code;
   }

   /**
   * @param code new value of {@link #getcode}.
   */
   public void setCode(String code) {

      this.code = code;
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

}
