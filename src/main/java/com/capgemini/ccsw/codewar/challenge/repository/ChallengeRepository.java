package com.capgemini.ccsw.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeEntity;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTo;

public interface ChallengeRepository extends CrudRepository<ChallengeEntity, Long> {

  @Query(value = "select new com.capgemini.ccsw.codewar.challenge.to.ChallengeTo(id, user.id, status.id, status.name, status.code, name, creationDate, startDate, endDate, multipleTries, (select count(1) from ParticipationEntity p where challenge.id = c.id)) "
      + "from ChallengeEntity c where user.id = ?1 order by status.id, startDate DESC, creationDate desc")
  List<ChallengeTo> findChallengesWithParticipationNum(Long id);

}