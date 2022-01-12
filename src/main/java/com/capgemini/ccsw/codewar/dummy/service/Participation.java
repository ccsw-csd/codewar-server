package com.capgemini.ccsw.codewar.dummy.service;

import com.capgemini.ccsw.codewar.dummy.to.ChallengeParticipationExecutionTo;
import com.capgemini.ccsw.codewar.dummy.to.ChallengeParticipationTo;

public interface Participation {

   /**
    * Recupera una tupla reto-participacion-usuario (la última participación del usuario si tiene varias)
    * @param id
    * @return
    */
   ChallengeParticipationTo getChallengeParticipation(long id, String username);

   /**
    * Ejecuta el código para un reto
    * @param id
    * @param code
    * @return
    */
   ChallengeParticipationExecutionTo executeChallengeParticipation(long id, String code);

}
