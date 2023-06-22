package com.ccsw.codewar.participation;

import java.util.List;

import com.ccsw.codewar.participation.model.Participation;

public interface ParticipationService {

    List<Participation> findAll();

    Participation findById(Long id);
}
