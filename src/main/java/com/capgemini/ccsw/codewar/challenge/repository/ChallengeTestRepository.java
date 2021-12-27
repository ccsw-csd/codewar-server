package com.capgemini.ccsw.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeTestEntity;

public interface ChallengeTestRepository extends CrudRepository<ChallengeTestEntity, Long> {

  List<ChallengeTestEntity> findByChallenge_IdOrderByOrder(long id);
}