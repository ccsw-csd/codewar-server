package com.ccsw.codewar.challenge.to;

import java.io.Serializable;

public class ChallengeParameterTo implements Serializable {

   private static final long serialVersionUID = 1L;

   private String type;

   private String name;

   public ChallengeParameterTo() {

   }

   public ChallengeParameterTo(String type, String name) {
      super();
      this.type = type;
      this.name = name;
   }

   /**
    * @return the type
    */
   public String getType() {
      return type;
   }

   /**
    * @param type the type to set
    */
   public void setType(String type) {
      this.type = type;
   }

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

}
