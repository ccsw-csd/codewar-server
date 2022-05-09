package com.ccsw.codewar.participation.service;

import com.ccsw.codewar.participation.to.ChallengeParticipationExecutionTo;
import com.ccsw.codewar.participation.to.ChallengeParticipationTo;

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

   /**
    * Realiza una inscripción del usuario y el código en un reto, el resultado será una participación
    * @param id
    * @param code
    * @return
    */
   void sendChallengeParticipation(long id, String code);

}
