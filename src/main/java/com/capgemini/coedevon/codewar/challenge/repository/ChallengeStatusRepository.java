package com.capgemini.coedevon.codewar.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.coedevon.codewar.challenge.model.ChallengeStatusEntity;

public interface ChallengeStatusRepository extends CrudRepository<ChallengeStatusEntity, Long> {

}