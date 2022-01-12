package com.capgemini.ccsw.codewar.compiler.to;

/**
 * @author pajimene
 *
 */
public class SystemCommandResultTo {

   private String out;

   private int exitValue;

   private boolean timeout;

   private long executionTime;

   public SystemCommandResultTo() {

   }

   /**
   * @return out
   */
   public String getOut() {

      return this.out;
   }

   /**
   * @param out new value of {@link #getout}.
   */
   public void setOut(String out) {

      this.out = out;
   }

   /**
   * @return exitValue
   */
   public int getExitValue() {

      return this.exitValue;
   }

   /**
   * @param exitValue new value of {@link #getexitValue}.
   */
   public void setExitValue(int exitValue) {

      this.exitValue = exitValue;
   }

   /**
   * @return timeout
   */
   public boolean isTimeout() {

      return this.timeout;
   }

   /**
   * @param timeout new value of {@link #gettimeout}.
   */
   public void setTimeout(boolean timeout) {

      this.timeout = timeout;
   }

   /**
   * @return executionTime
   */
   public long getExecutionTime() {

      return executionTime;
   }

   /**
   * @param executionTime new value of {@link #getexecutionTime}.
   */
   public void setExecutionTime(long executionTime) {

      this.executionTime = executionTime;
   }

}
