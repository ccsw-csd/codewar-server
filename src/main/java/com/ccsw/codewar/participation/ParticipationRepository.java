package com.ccsw.codewar.participation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.codewar.participation.model.Participation;

public interface ParticipationRepository
        extends CrudRepository<Participation, Long>, JpaRepository<Participation, Long> {
    public List<Participation> findAll();

}
