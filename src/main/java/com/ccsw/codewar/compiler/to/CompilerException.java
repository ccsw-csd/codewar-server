package com.ccsw.codewar.compiler.to;

public class CompilerException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
   * The constructor.
   * @param message
   * @param cause
   */
   public CompilerException(String message, Throwable cause) {

      super(message, cause);
   }

   /**
   * The constructor.
   * @param message
   */
   public CompilerException(String message) {

      super(message);
   }

}
