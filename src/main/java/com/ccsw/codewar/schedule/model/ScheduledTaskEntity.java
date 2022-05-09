package com.ccsw.codewar.schedule.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "codewar", name = "scheduled_task")
public class ScheduledTaskEntity {

   @Id
   @Column(name = "id")
   private Long id;

   @Enumerated(EnumType.ORDINAL)
   private ScheduledTaskStatus status;

   @Enumerated(EnumType.ORDINAL)
   private ScheduledTaskType type;

   @Column(name = "data")
   private String data;

   @Column(name = "start_time")
   private Date startTime;

   @Column(name = "end_time")
   private Date endTime;

   /**
    * @return the id
    */
   public Long getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Long id) {
      this.id = id;
   }

   /**
    * @return the status
    */
   public ScheduledTaskStatus getStatus() {
      return status;
   }

   /**
    * @param status the status to set
    */
   public void setStatus(ScheduledTaskStatus status) {
      this.status = status;
   }

   /**
    * @return the type
    */
   public ScheduledTaskType getType() {
      return type;
   }

   /**
    * @param type the type to set
    */
   public void setType(ScheduledTaskType type) {
      this.type = type;
   }

   /**
    * @return the data
    */
   public String getData() {
      return data;
   }

   /**
    * @param data the data to set
    */
   public void setData(String data) {
      this.data = data;
   }

   /**
    * @return the startTime
    */
   public Date getStartTime() {
      return startTime;
   }

   /**
    * @param startTime the startTime to set
    */
   public void setStartTime(Date startTime) {
      this.startTime = startTime;
   }

   /**
    * @return the endTime
    */
   public Date getEndTime() {
      return endTime;
   }

   /**
    * @param endTime the endTime to set
    */
   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

}
