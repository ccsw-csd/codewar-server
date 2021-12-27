package com.capgemini.ccsw.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeTestValueEntity;

public interface ChallengeTestValueRepository extends CrudRepository<ChallengeTestValueEntity, Long> {

  List<ChallengeTestValueEntity> findByTest_idOrderByParameter_Order(long id);

}