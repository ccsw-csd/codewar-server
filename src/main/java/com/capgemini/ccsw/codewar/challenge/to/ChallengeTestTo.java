package com.capgemini.ccsw.codewar.challenge.to;

import java.io.Serializable;
import java.util.List;

public class ChallengeTestTo implements Serializable {

   private static final long serialVersionUID = 1L;

   private String name;

   private Integer timeout;

   private Boolean visible;

   private Boolean performance;

   private ChallengeTestValueTo valueOut;

   private List<ChallengeTestValueTo> valueIn;

   /**
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * @param name the name to set
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * @return the timeout
    */
   public Integer getTimeout() {
      return timeout;
   }

   /**
    * @param timeout the timeout to set
    */
   public void setTimeout(Integer timeout) {
      this.timeout = timeout;
   }

   /**
    * @return the visible
    */
   public Boolean getVisible() {
      return visible;
   }

   /**
    * @param visible the visible to set
    */
   public void setVisible(Boolean visible) {
      this.visible = visible;
   }

   /**
    * @return the valueOut
    */
   public ChallengeTestValueTo getValueOut() {
      return valueOut;
   }

   /**
    * @param valueOut the valueOut to set
    */
   public void setValueOut(ChallengeTestValueTo valueOut) {
      this.valueOut = valueOut;
   }

   /**
    * @return the valueIn
    */
   public List<ChallengeTestValueTo> getValueIn() {
      return valueIn;
   }

   /**
    * @param valueIn the valueIn to set
    */
   public void setValueIn(List<ChallengeTestValueTo> valueIn) {
      this.valueIn = valueIn;
   }

   /**
    * @return the performance
    */
   public Boolean getPerformance() {
      return performance;
   }

   /**
    * @param performance the performance to set
    */
   public void setPerformance(Boolean performance) {
      this.performance = performance;
   }

}
