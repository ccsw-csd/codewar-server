package com.capgemini.ccsw.codewar.compiler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.ccsw.codewar.compiler.to.CodeDiagnosticTo;
import com.capgemini.ccsw.codewar.compiler.to.CompilerException;
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
      }
   }

}
