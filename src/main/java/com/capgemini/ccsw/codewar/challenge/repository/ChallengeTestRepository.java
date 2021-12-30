package com.capgemini.ccsw.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeTestEntity;

public interface ChallengeTestRepository extends CrudRepository<ChallengeTestEntity, Long> {

   List<ChallengeTestEntity> findByChallenge_IdOrderByOrder(long id);

   @Modifying
   @Query("delete from ChallengeTestEntity f where f.challenge.id =:challengeId")
   void deleteByChallengeId(@Param("challengeId") Long id);
}