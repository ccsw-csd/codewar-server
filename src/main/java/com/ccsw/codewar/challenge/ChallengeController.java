package com.ccsw.codewar.challenge;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.codewar.challenge.model.Challenge;
import com.ccsw.codewar.challenge.model.ChallengeItemListDto;
import com.ccsw.codewar.challenge.model.ChallengeEditDto;

@RequestMapping(value = "/challenge")
@RestController
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    @Autowired
    DozerBeanMapper mapper;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ChallengeItemListDto> findAll() {

        List<Challenge> challenges = this.challengeService.findAll();

        return challenges.stream().map(e -> mapper.map(e, ChallengeItemListDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ChallengeEditDto findById(@PathVariable Long id) {
        Challenge challenge = this.challengeService.findById(id);
        return mapper.map(challenge, ChallengeEditDto.class);
    }
}
