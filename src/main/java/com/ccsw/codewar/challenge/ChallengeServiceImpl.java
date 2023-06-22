package com.ccsw.codewar.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.codewar.challenge.model.Challenge;
import com.ccsw.codewar.participation.ParticipationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ParticipationRepository participationRepository;

    @Override
    public List<Challenge> findAll() {
        return this.challengeRepository.findAll();
    }

    @Override
    public Challenge findById(Long id) {

        return this.challengeRepository.findById(id).orElse(null);
    }
}
