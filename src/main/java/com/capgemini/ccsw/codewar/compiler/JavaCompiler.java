package com.capgemini.ccsw.codewar.compiler;

import java.util.List;

import com.capgemini.ccsw.codewar.compiler.to.CodeDiagnosticTo;
import com.capgemini.ccsw.codewar.compiler.to.CompilerException;

public interface JavaCompiler {

   /**
    * Crea una compilación en memoria de la clase java con el código que se le pasa y devuelve resultados
    * @param code
    * @param className
    * @return Si no devuelve nada es porque la compilación es correcta
    * @throws CompilerException
    */
   List<CodeDiagnosticTo> compileInMemory(String code, String className) throws CompilerException;
}
