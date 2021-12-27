package com.capgemini.ccsw.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeParameterEntity;

public interface ChallengeParameterRepository extends CrudRepository<ChallengeParameterEntity, Long> {

  List<ChallengeParameterEntity> findByChallenge_IdOrderByOrder(long id);

}