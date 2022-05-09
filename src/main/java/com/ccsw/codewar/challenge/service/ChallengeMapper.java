package com.ccsw.codewar.challenge.service;

import java.util.List;

import com.ccsw.codewar.challenge.to.ChallengeParameterTo;
import com.ccsw.codewar.challenge.to.ChallengeTestTo;
import com.ccsw.codewar.master.to.TagTo;

public interface ChallengeMapper {

   /**
    * Recupera los testsTO de un challenge
    * @param id
    * @return
    */
   List<ChallengeTestTo> findTestsByChallengeId(long id);

   /**
    * Recupera los TagsTo de un challenge
    * @param id
    * @return
    */
   List<TagTo> findTagsByChallengeId(long id);

   /**
    * Recupera los ParameterTo de salida de un challenge
    * @param id
    * @return
    */
   ChallengeParameterTo findOutParameterByChallengeId(long id);

   /**
    * Recupera los ParameterTO de entrada de un challenge
    * @param id
    * @return
    */
   List<ChallengeParameterTo> findParametersByChallengeId(long id);

}
