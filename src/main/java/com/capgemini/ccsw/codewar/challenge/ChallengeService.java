package com.capgemini.ccsw.codewar.challenge;

import java.util.List;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeStatusEntity;
import com.capgemini.ccsw.codewar.challenge.model.ParameterTypeEntity;
import com.capgemini.ccsw.codewar.challenge.model.TagEntity;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTo;

public interface ChallengeService {

  /**
   * Recupera toda la estructura de un challenge
   * @param id
   * @return
   */
  ChallengeTo get(long id);

  /**
   * Guarda toda la estructura de un challenge
   * @param challenge
   * @return
   */
  ChallengeTo saveOrUpdateUser(ChallengeTo challenge);

  /**
   * Recupera todos los tags disponibles
   * @return
   */
  List<TagEntity> findAllTags();

  /**
   * Recupera todos los tipos de parametros disponibles
   * @return
   */
  List<ParameterTypeEntity> findAllParameterType();

  /**
   * Recupera todos los estados de challenge disponibles
   * @return
   */
  List<ChallengeStatusEntity> findAllChallengeStatus();

  /**
   * Recupera todos los challenges de un usuario
   * @return
   */
  List<ChallengeTo> find();

}
