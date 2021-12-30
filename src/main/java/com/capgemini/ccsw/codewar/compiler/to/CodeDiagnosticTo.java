package com.capgemini.ccsw.codewar.compiler.to;

import java.io.Serializable;

public class CodeDiagnosticTo implements Serializable {
   /**
   *
   */
   private static final long serialVersionUID = 1L;

   private String code;
   private String kind;
   private Long position;
   private Long linePosition;
   private String file;
   private String errorMessage;

   /**
   * @return the code
   */
   public String getCode() {
      return code;
   }

   /**
   * @param code the code to set
   */
   public void setCode(String code) {
      this.code = code;
   }

   /**
   * @return the kind
   */
   public String getKind() {
      return kind;
   }

   /**
   * @param kind the kind to set
   */
   public void setKind(String kind) {
      this.kind = kind;
   }

   /**
   * @return the position
   */
   public Long getPosition() {
      return position;
   }

   /**
   * @param position the position to set
   */
   public void setPosition(Long position) {
      this.position = position;
   }

   /**
   * @return the linePosition
   */
   public Long getLinePosition() {
      return linePosition;
   }

   /**
   * @param linePosition the linePosition to set
   */
   public void setLinePosition(Long linePosition) {
      this.linePosition = linePosition;
   }

   /**
   * @return the file
   */
   public String getFile() {
      return "In: " + file + ".java";
   }

   /**
   * @param file the file to set
   */
   public void setFile(String file) {
      this.file = file;
   }

   /**
   * @return the errorMessage
   */
   public String getErrorMessage() {
      return errorMessage;
   }

   /**
   * @param errorMessage the errorMessage to set
   */
   public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
   }

}
