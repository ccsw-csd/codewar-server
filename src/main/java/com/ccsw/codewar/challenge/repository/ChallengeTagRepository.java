package com.ccsw.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.codewar.challenge.model.ChallengeTagEntity;

public interface ChallengeTagRepository extends CrudRepository<ChallengeTagEntity, Long> {

   List<ChallengeTagEntity> findByChallenge_Id(long id);

   @Modifying
   @Query("delete from ChallengeTagEntity f where f.challenge.id =:challengeId")
   void deleteByChallengeId(@Param("challengeId") Long id);

}