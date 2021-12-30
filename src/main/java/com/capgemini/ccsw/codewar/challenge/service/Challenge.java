package com.capgemini.ccsw.codewar.challenge.service;

import java.util.List;

import com.capgemini.ccsw.codewar.challenge.to.ChallengeActivateResponseTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeItemListTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;

public interface Challenge {

   /**
   * Recupera toda la estructura de un challenge
   * @param id
   * @return
   */
   ChallengeSaveTo get(long id);

   /**
    * Recupera todos los challenges de un usuario
    * @return
    */
   List<ChallengeItemListTo> find();

   /**
   * Guarda toda la estructura de un challenge
   * @param id
   * @param challenge
   * @return
   */
   ChallengeSaveTo saveOrUpdate(Long id, ChallengeSaveTo challenge);

   /**
    * Elimina toda la estructura de un challenge
    * @param id
    * @return
    */
   void delete(Long id);

   /**
    * Comprueba si un challenge es correcto y se puede activar
    * @param id
    * @return
    */
   ChallengeActivateResponseTo check(Long id);

   /**
    * Comprueba si un challenge es correcto y lo activa a fecha actual
    * @param id
    * @return
    */
   ChallengeActivateResponseTo checkAndActivate(Long id);

}
