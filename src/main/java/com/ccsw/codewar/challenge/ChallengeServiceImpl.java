package com.ccsw.codewar.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.codewar.challenge.model.Challenge;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;

    @Override
    public List<Challenge> findAll() {

        return (List<Challenge>) this.challengeRepository.findChallengeByOrderByStatusIdAscEndDateAscCreatedDateAsc();
    }
}
