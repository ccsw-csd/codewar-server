package com.capgemini.ccsw.codewar.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeStatusEntity;

public interface ChallengeStatusRepository extends CrudRepository<ChallengeStatusEntity, Long> {

}