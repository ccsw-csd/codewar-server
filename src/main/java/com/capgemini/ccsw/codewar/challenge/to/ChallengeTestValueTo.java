package com.capgemini.ccsw.codewar.challenge.to;

import java.io.Serializable;

public class ChallengeTestValueTo implements Serializable {

   private static final long serialVersionUID = 1L;

   private String value;
   private String name;
   private String type;

   /**
    * @return the value
    */
   public String getValue() {
      return value;
   }

   /**
    * @param value the value to set
    */
   public void setValue(String value) {
      this.value = value;
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

}
