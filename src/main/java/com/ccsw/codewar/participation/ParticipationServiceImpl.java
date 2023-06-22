package com.ccsw.codewar.participation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.codewar.participation.model.Participation;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    ParticipationRepository participationRepository;

    @Override
    public List<Participation> findAll() {

        return (List<Participation>) this.participationRepository.findAll();
    }

    @Override
    public Participation findById(Long id) {

        return this.participationRepository.findById(id).orElse(null);
    }
}
