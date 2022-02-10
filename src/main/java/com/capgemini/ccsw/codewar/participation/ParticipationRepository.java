package com.capgemini.ccsw.codewar.participation;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.participation.model.ParticipationEntity;

public interface ParticipationRepository extends CrudRepository<ParticipationEntity, Long> {

}
