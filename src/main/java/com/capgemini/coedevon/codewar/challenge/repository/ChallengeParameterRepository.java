package com.capgemini.coedevon.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.coedevon.codewar.challenge.model.ChallengeParameterEntity;

public interface ChallengeParameterRepository extends CrudRepository<ChallengeParameterEntity, Long> {

  List<ChallengeParameterEntity> findByChallenge_IdOrderByOrder(long id);

}