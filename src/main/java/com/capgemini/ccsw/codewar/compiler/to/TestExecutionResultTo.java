package com.capgemini.ccsw.codewar.compiler.to;

import java.util.stream.Collectors;

import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestValueTo;

public class TestExecutionResultTo {

   private String name;

   private long executionTime;

   private boolean testFail;

   private boolean testTimeout;

   private String inParameter;

   private String outParameterExpected;

   private String outParameterActual;

   private String consoleOut;

   public TestExecutionResultTo(ChallengeTestTo test, int timeout) {
      setTestData(test);

      this.executionTime = timeout;
      this.testFail = true;
      this.testTimeout = true;
   }

   public TestExecutionResultTo(ChallengeTestTo test, SystemCommandResultTo result) {

      setTestData(test);

      this.executionTime = result.getExecutionTime();
      this.consoleOut = result.getOut();
      this.outParameterActual = result.getErr();
      this.testFail = result.getExitValue() != 1;
      this.testTimeout = result.isTimeout();
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
    * @return the executionTime
    */
   public long getExecutionTime() {
      return executionTime;
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

}
