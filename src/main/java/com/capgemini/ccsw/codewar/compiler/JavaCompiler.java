package com.capgemini.ccsw.codewar.compiler;

import java.util.List;

import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.capgemini.ccsw.codewar.compiler.to.CodeDiagnosticTo;
import com.capgemini.ccsw.codewar.compiler.to.CompilerException;
import com.capgemini.ccsw.codewar.compiler.to.TestExecutionResultTo;

public interface JavaCompiler {

   /**
    * Crea una compilación en memoria de la clase java con el código que se le pasa y devuelve resultados
    * @param code
    * @param className
    * @return Si no devuelve nada es porque la compilación es correcta
    * @throws CompilerException
    */
   List<CodeDiagnosticTo> compileInMemory(String code, String className) throws CompilerException;

   /**
    * Crea una compilación del código y ejecuta los tests del reto
    * @param challenge
    * @param allTests Si está a true, ejecutará todos los tests del reto, si está a false ejecutará únicamente los tests visibles
    * @return 
    * @throws CompilerException
    */
   List<TestExecutionResultTo> compileAndExecuteTests(String code, ChallengeSaveTo challenge, boolean allTests) throws CompilerException;
}
