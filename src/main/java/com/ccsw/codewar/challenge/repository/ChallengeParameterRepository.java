package com.ccsw.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.codewar.challenge.model.ChallengeParameterEntity;

public interface ChallengeParameterRepository extends CrudRepository<ChallengeParameterEntity, Long> {

   List<ChallengeParameterEntity> findByChallenge_IdOrderByOrder(long id);

   @Modifying
   @Query("delete from ChallengeParameterEntity f where f.challenge.id =:challengeId")
   void deleteByChallengeId(@Param("challengeId") Long id);

}