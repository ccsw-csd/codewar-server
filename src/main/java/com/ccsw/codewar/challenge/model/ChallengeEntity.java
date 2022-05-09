package com.ccsw.codewar.challenge.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.codewar.master.model.StatusEntity;
import com.ccsw.codewar.user.model.UserEntity;

@Entity
@Table(schema = "codewar", name = "challenge")
public class ChallengeEntity {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private UserEntity user;

   @ManyToOne
   @JoinColumn(name = "status_id")
   private StatusEntity status;

   @Column(name = "creation_date")
   private Date creationDate;

   @Column(name = "name")
   private String name;

   @Column(name = "description")
   private String description;

   @Column(name = "base_code")
   private String baseCode;

   @Column(name = "function_name")
   private String functionName;

   @Column(name = "class_name")
   private String className;

   @Column(name = "start_date")
   private Date startDate;

   @Column(name = "end_date")
   private Date endDate;

   @Column(name = "tries")
   private Boolean multipleTries;

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
   * @return status
   */
   public StatusEntity getStatus() {

      return this.status;
   }

   /**
   * @param status new value of {@link #getstatus}.
   */
   public void setStatus(StatusEntity status) {

      this.status = status;
   }

   /**
   * @return creationDate
   */
   public Date getCreationDate() {

      return this.creationDate;
   }

   /**
   * @param creationDate new value of {@link #getcreationDate}.
   */
   public void setCreationDate(Date creationDate) {

      this.creationDate = creationDate;
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
   * @return description
   */
   public String getDescription() {

      return this.description;
   }

   /**
   * @param description new value of {@link #getdescription}.
   */
   public void setDescription(String description) {

      this.description = description;
   }

   /**
   * @return baseCode
   */
   public String getBaseCode() {

      return this.baseCode;
   }

   /**
   * @param baseCode new value of {@link #getbaseCode}.
   */
   public void setBaseCode(String baseCode) {

      this.baseCode = baseCode;
   }

   /**
   * @return functionName
   */
   public String getFunctionName() {

      return this.functionName;
   }

   /**
   * @param functionName new value of {@link #getfunctionName}.
   */
   public void setFunctionName(String functionName) {

      this.functionName = functionName;
   }

   /**
   * @return className
   */
   public String getClassName() {

      return this.className;
   }

   /**
   * @param className new value of {@link #getclassName}.
   */
   public void setClassName(String className) {

      this.className = className;
   }

   /**
   * @return startDate
   */
   public Date getStartDate() {

      return this.startDate;
   }

   /**
   * @param startDate new value of {@link #getstartDate}.
   */
   public void setStartDate(Date startDate) {

      this.startDate = startDate;
   }

   /**
   * @return endDate
   */
   public Date getEndDate() {

      return this.endDate;
   }

   /**
   * @param endDate new value of {@link #getendDate}.
   */
   public void setEndDate(Date endDate) {

      this.endDate = endDate;
   }

   /**
   * @return multipleTries
   */
   public Boolean getMultipleTries() {

      return this.multipleTries;
   }

   /**
   * @param multipleTries new value of {@link #getmultipleTries}.
   */
   public void setMultipleTries(Boolean multipleTries) {

      this.multipleTries = multipleTries;
   }

}
