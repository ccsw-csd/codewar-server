package com.capgemini.ccsw.codewar.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeEntity;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeItemListTo;

public interface ChallengeRepository extends CrudRepository<ChallengeEntity, Long> {

   @Query(value = "select c.id as id, cs.name as statusName, cs.code as statusCode, c.name as name, " //
         + "c.start_date as startDate, c.end_date as endDate, c.tries as multipleTries, " //
         + "(select count(1) from participation p where challenge_id = c.id) as numParticipation, " //
         + "(select group_concat(t.name) from challenge_tag ct join tag t on t.id = ct.tag_id where ct.challenge_id = c.id) as tagsName " //
         + "from challenge c join challenge_status cs on c.status_id = cs.id " //
         + "order by status_id, start_date DESC, creation_date desc", nativeQuery = true)
   List<ChallengeItemListTo> findChallengesWithParticipationNum();

}