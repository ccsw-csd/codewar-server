package com.ccsw.codewar.challenge.to;

import java.io.Serializable;
import java.util.List;

import com.ccsw.codewar.master.to.TagTo;

public class ChallengeSaveTo implements Serializable {

   private static final long serialVersionUID = 1L;

   private String statusCode;

   private String name;

   private String description;

   private String functionName;

   private String className;

   private Boolean multipleTries;

   private List<TagTo> tags;

   private ChallengeParameterTo outParameter;

   private List<ChallengeParameterTo> inParameter;

   private List<ChallengeTestTo> test;

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

   /**
   * @return tags
   */
   public List<TagTo> getTags() {

      return this.tags;
   }

   /**
   * @param tags new value of {@link #gettags}.
   */
   public void setTags(List<TagTo> tags) {

      this.tags = tags;
   }

   public ChallengeParameterTo getOutParameter() {
      return outParameter;
   }

   public void setOutParameter(ChallengeParameterTo outParameter) {
      this.outParameter = outParameter;
   }

   public List<ChallengeParameterTo> getInParameter() {
      return inParameter;
   }

   public void setInParameter(List<ChallengeParameterTo> inParameter) {
      this.inParameter = inParameter;
   }

   public List<ChallengeTestTo> getTest() {
      return test;
   }

   public void setTest(List<ChallengeTestTo> test) {
      this.test = test;
   }

   /**
    * @return the statusCode
    */
   public String getStatusCode() {
      return statusCode;
   }

   /**
    * @param statusCode the statusCode to set
    */
   public void setStatusCode(String statusCode) {
      this.statusCode = statusCode;
   }

}
