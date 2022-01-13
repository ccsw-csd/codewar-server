package com.capgemini.ccsw.codewar.compiler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.capgemini.ccsw.codewar.compiler.to.CodeDiagnosticTo;
import com.capgemini.ccsw.codewar.compiler.to.CompilerException;
import com.capgemini.ccsw.codewar.compiler.to.TestExecutionResultTo;
import com.google.common.io.Files;

@Service
public class JavaCompilerImpl implements JavaCompiler {

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

      } catch (IOException e) {
         throw new CompilerException("Error en la compilacion");
      } finally {
         compilationPath.delete();
      }

      return null;
   }

}
