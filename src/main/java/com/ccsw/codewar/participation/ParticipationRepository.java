package com.ccsw.codewar.participation;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.codewar.participation.model.ParticipationEntity;

public interface ParticipationRepository extends CrudRepository<ParticipationEntity, Long> {

}
