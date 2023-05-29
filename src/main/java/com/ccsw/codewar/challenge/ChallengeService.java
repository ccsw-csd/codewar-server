package com.ccsw.codewar.challenge;

import java.util.List;

import com.ccsw.codewar.challenge.model.Challenge;

public interface ChallengeService {

    List<Challenge> findAll();

    Challenge findById(Long id);

}
