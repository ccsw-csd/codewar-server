package com.capgemini.ccsw.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeTestValueEntity;

public interface ChallengeTestValueRepository extends CrudRepository<ChallengeTestValueEntity, Long> {

   List<ChallengeTestValueEntity> findByTest_idOrderByParameter_Order(long id);

   @Modifying
   @Query("delete from ChallengeTestValueEntity f where f.test.id in (select id from ChallengeTestEntity where challenge.id =:challengeId)")
   void deleteByChallengeId(@Param("challengeId") Long id);

}