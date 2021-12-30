package com.capgemini.ccsw.codewar.challenge.service;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeEntity;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;

public interface ChallengeSaver {

   /**
    * Elimina los datos hijos de un challenge
    * @param id
    */
   void removeChallengeData(Long id);

   /**
    * Guarda el listado de TagsTo asociado a un challenge
    * @param challenge
    * @param challengeTo
    */
   void saveTags(ChallengeEntity challenge, ChallengeSaveTo challengeTo);

   /**
    * Guarda los listados de ParameterTo y TestTo asociados a un challenge
    * @param challenge
    * @param challengeTo
    */
   void saveParametersAndTests(ChallengeEntity challenge, ChallengeSaveTo challengeTo);

}
