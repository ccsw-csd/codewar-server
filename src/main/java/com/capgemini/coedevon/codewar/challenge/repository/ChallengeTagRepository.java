package com.capgemini.coedevon.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.coedevon.codewar.challenge.model.ChallengeTagEntity;

public interface ChallengeTagRepository extends CrudRepository<ChallengeTagEntity, Long> {

  List<ChallengeTagEntity> findByChallenge_Id(long id);

}