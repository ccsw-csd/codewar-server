package com.capgemini.coedevon.codewar.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.coedevon.codewar.challenge.model.ChallengeStatusEntity;
import com.capgemini.coedevon.codewar.challenge.model.ParameterTypeEntity;
import com.capgemini.coedevon.codewar.challenge.to.ChallengeTo;
import com.capgemini.coedevon.codewar.challenge.to.TagTo;
import com.capgemini.coedevon.codewar.configuration.mapper.BeanMapper;
import com.capgemini.coedevon.codewar.user.UserService;

/**
 * The service implementation for REST calls in order to execute the logic of
 * component {@link UserService}.
 *
 * @author rroigped
 */
@RequestMapping(value = "/challenge/")
@RestController
public class ChallengeController {

  @Autowired
  private ChallengeService challengeService;

  @Autowired
  private BeanMapper beanMapper;

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public ChallengeTo get(@PathVariable("id") long id) {

    return this.challengeService.get(id);

  }

  @RequestMapping(path = "/", method = RequestMethod.GET)
  public List<ChallengeTo> find() {

    return this.challengeService.find();

  }

  @RequestMapping(path = "/", method = RequestMethod.POST)
  public ChallengeTo saveOrUpdateUser(@RequestBody ChallengeTo challenge) {

    return this.challengeService.saveOrUpdateUser(challenge);
  }

  @RequestMapping(path = "/tags", method = RequestMethod.GET)
  List<TagTo> findAllTags() {

    return this.beanMapper.mapList(this.challengeService.findAllTags(), TagTo.class);

  }

  @RequestMapping(path = "/parameter-types", method = RequestMethod.GET)
  List<ParameterTypeEntity> findAllParameterType() {

    return this.beanMapper.mapList(this.challengeService.findAllParameterType(), ParameterTypeEntity.class);

  }

  @RequestMapping(path = "/status", method = RequestMethod.GET)
  List<ChallengeStatusEntity> findAllChallengeStatus() {

    return this.beanMapper.mapList(this.challengeService.findAllChallengeStatus(), ChallengeStatusEntity.class);

  }

}