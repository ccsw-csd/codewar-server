package com.ccsw.codewar.challenge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.codewar.challenge.model.Challenge;

public interface ChallengeRepository extends CrudRepository<Challenge, Long>, JpaRepository<Challenge, Long> {
    public List<Challenge> findAllOrderByStatusIdAscAndEndDateAscAndCreatedDateAsc();
}
