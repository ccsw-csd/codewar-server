package com.ccsw.codewar.challenge.to;

import java.util.List;

public class ChallengeActivateResponseTo {

   private boolean valid;

   private List<String> errors;

   public ChallengeActivateResponseTo(List<String> errors) {
      this.errors = errors;
      this.valid = errors == null || errors.size() == 0;
   }

   public List<String> getErrors() {
      return errors;
   }

   public boolean isValid() {
      return this.valid;
   }

}
