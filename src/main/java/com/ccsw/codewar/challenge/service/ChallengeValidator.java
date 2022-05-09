package com.ccsw.codewar.challenge.service;

import com.ccsw.codewar.challenge.to.ChallengeActivateResponseTo;
import com.ccsw.codewar.challenge.to.ChallengeSaveTo;

public interface ChallengeValidator {

   /**
    * Comprueba los errores de un reto y devuelve un listado de ellos
    * @param challenge
    * @return
    */
   ChallengeActivateResponseTo check(ChallengeSaveTo challenge);

   /**
    * Genera el código base de un reto
    * @param challenge
    * @return
    */
   String generateBaseCode(ChallengeSaveTo challenge);
}
