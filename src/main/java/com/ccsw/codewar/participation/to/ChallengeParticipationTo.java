package com.ccsw.codewar.participation.to;

import java.io.Serializable;

import com.ccsw.codewar.challenge.to.ChallengeMinimalTo;

public class ChallengeParticipationTo implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private ChallengeMinimalTo challenge;
   //private ParticipationMinimalTo participation; //TODO: Falta por implementar

   /**
    * @return the challenge
    */
   public ChallengeMinimalTo getChallenge() {
      return challenge;
   }

   /**
    * @param challenge the challenge to set
    */
   public void setChallenge(ChallengeMinimalTo challenge) {
      this.challenge = challenge;
   }

}
