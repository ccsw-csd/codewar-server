package com.capgemini.ccsw.codewar.dummy.to;

import java.util.List;

import com.capgemini.ccsw.codewar.compiler.to.CodeDiagnosticTo;
import com.capgemini.ccsw.codewar.compiler.to.TestExecutionResultTo;

public class ChallengeParticipationExecutionTo {

   private boolean compileError;
   private List<CodeDiagnosticTo> errors;
   private List<TestExecutionResultTo> testExecutionResult;

   public ChallengeParticipationExecutionTo(boolean compileError) {
      this.compileError = compileError;
   }

   public ChallengeParticipationExecutionTo(List<CodeDiagnosticTo> errors) {
      this(errors != null && errors.size() > 0);

      this.errors = errors;
   }

   /**
    * @return the compileError
    */
   public boolean isCompileError() {
      return compileError;
   }

   /**
    * @return the errors
    */
   public List<CodeDiagnosticTo> getErrors() {
      return errors;
   }

   /**
    * @return the testExecutionResult
    */
   public List<TestExecutionResultTo> getTestExecutionResult() {
      return testExecutionResult;
   }

   /**
    * @param testExecutionResult the testExecutionResult to set
    */
   public void setTestExecutionResult(List<TestExecutionResultTo> testExecutionResult) {
      this.testExecutionResult = testExecutionResult;
   }
}
