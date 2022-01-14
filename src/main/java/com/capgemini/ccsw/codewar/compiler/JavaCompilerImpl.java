package com.capgemini.ccsw.codewar.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestValueTo;
import com.capgemini.ccsw.codewar.compiler.to.CodeDiagnosticTo;
import com.capgemini.ccsw.codewar.compiler.to.CompilerException;
import com.capgemini.ccsw.codewar.compiler.to.SystemCommandResultTo;
import com.capgemini.ccsw.codewar.compiler.to.TestExecutionResultTo;
import com.google.common.io.Files;

@Service
public class JavaCompilerImpl implements JavaCompiler {

   private static final Logger LOG = LoggerFactory.getLogger(JavaCompilerImpl.class);

   /**
    * {@inheritDoc}
    */
   @Override
   public List<CodeDiagnosticTo> compileInMemory(String code, String className) throws CompilerException {

      File compilationPath = Files.createTempDir();

      try {
         return JavaClassUtils.compileJavaContentInMemory(code, className, compilationPath);
      } catch (IOException e) {
         throw new CompilerException("Error en la compilacion");
      } finally {
         compilationPath.delete();
      }
   }

   @Override
   public List<TestExecutionResultTo> compileAndExecuteTests(String code, ChallengeSaveTo challenge, boolean allTests) throws CompilerException {

      File compilationPath = Files.createTempDir();
      String className = challenge.getClassName();

      try {
         List<CodeDiagnosticTo> compileErrors = JavaClassUtils.compileJavaContentInMemory(code, className, compilationPath);

         if (compileErrors != null && compileErrors.size() > 0)
            throw new CompilerException("Error en la compilacion");

         JavaClassUtils.createAndCompileMainClass(challenge, compilationPath);

         return executeTests(compilationPath, challenge, allTests);

      } catch (IOException e) {
         throw new CompilerException("Error en la compilacion");
      } finally {
         compilationPath.delete();
      }

   }

   private List<TestExecutionResultTo> executeTests(File compilationPath, ChallengeSaveTo challenge, boolean allTests) {

      List<TestExecutionResultTo> testResult = new ArrayList<>();

      for (ChallengeTestTo test : challenge.getTest()) {
         if (mustRunTest(test, allTests)) {
            testResult.add(executeTest(compilationPath, test));
         }

      }

      return testResult;
   }

   private TestExecutionResultTo executeTest(File compilationPath, ChallengeTestTo test) {

      Integer timeout = test.getTimeout();
      if (timeout == null)
         timeout = 5000;

      String javaExecuteCommand = createJavaExecuteCommand(test);

      try {
         SystemCommandResultTo result = SystemCommandExecutor.ExecuteSyncCommand(javaExecuteCommand, compilationPath, timeout);

         return new TestExecutionResultTo(test, result);

      } catch (IOException e) {
         LOG.error("Error al ejecutar el test: " + e.getMessage());
         return new TestExecutionResultTo(test, timeout);
      }
   }

   private String createJavaExecuteCommand(ChallengeTestTo test) {

      String javaExecuteCommand = "java Main";

      ChallengeTestValueTo outParameter = test.getValueOut();
      javaExecuteCommand += " \"" + outParameter.getValue() + "\"";

      for (ChallengeTestValueTo inParameter : test.getValueIn()) {
         javaExecuteCommand += " \"" + inParameter.getValue() + "\"";
      }

      return javaExecuteCommand;
   }

   private boolean mustRunTest(ChallengeTestTo test, boolean allTests) {

      if (allTests)
         return true;

      return test.getVisible();
   }

}
