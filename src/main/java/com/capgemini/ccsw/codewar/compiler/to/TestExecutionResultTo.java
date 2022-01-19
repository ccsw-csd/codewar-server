package com.capgemini.ccsw.codewar.compiler.to;

import java.util.stream.Collectors;

import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestValueTo;

public class TestExecutionResultTo {

   private String name;

   private long executionTimeExpected;

   private long executionTimeActual;

   private boolean testFail;

   private boolean testTimeout;

   private String inParameter;

   private String outParameterExpected;

   private String outParameterActual;

   private String consoleOut = null;

   private boolean performance;

   public TestExecutionResultTo(ChallengeTestTo test, int timeout) {
      setTestData(test);

      this.executionTimeExpected = timeout;
      this.executionTimeActual = timeout;
      this.testFail = true;
      this.testTimeout = true;
   }

   public TestExecutionResultTo(ChallengeTestTo test, SystemCommandResultTo result) {

      setTestData(test);

      this.executionTimeActual = result.getExecutionTime();

      if (test.getTimeout() == null)
         this.executionTimeExpected = 5000;
      else
         this.executionTimeExpected = test.getTimeout().longValue();

      this.consoleOut = result.getOut();
      this.outParameterActual = result.getErr();
      this.testFail = result.getExitValue() != 1;
      this.testTimeout = result.isTimeout();
      this.performance = test.getPerformance();

      if (this.performance) {
         this.consoleOut = null;
         this.testTimeout = this.executionTimeActual > this.executionTimeExpected;
      }
   }

   private void setTestData(ChallengeTestTo test) {
      this.name = test.getName();
      this.inParameter = test.getValueIn().stream().map(ChallengeTestValueTo::getValue).collect(Collectors.joining(" | "));
      this.outParameterExpected = test.getValueOut().getValue();
   }

   /**
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * @return the testFail
    */
   public boolean isTestFail() {
      return testFail;
   }

   /**
    * @return the testTimeout
    */
   public boolean isTestTimeout() {
      return testTimeout;
   }

   /**
    * @return the inParameter
    */
   public String getInParameter() {
      return inParameter;
   }

   /**
    * @return the outParameterExpected
    */
   public String getOutParameterExpected() {
      return outParameterExpected;
   }

   /**
    * @return the outParameterActual
    */
   public String getOutParameterActual() {
      return outParameterActual;
   }

   /**
    * @return the consoleOut
    */
   public String getConsoleOut() {
      return consoleOut;
   }

   /**
    * @return the performance
    */
   public Boolean getPerformance() {
      return performance;
   }

   /**
    * @return the executionTimeExpected
    */
   public long getExecutionTimeExpected() {
      return executionTimeExpected;
   }

   /**
    * @return the executionTimeActual
    */
   public long getExecutionTimeActual() {
      return executionTimeActual;
   }

}
