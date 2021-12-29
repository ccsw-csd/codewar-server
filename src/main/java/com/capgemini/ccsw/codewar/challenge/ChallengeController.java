package com.capgemini.ccsw.codewar.challenge;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeStatusEntity;
import com.capgemini.ccsw.codewar.challenge.model.ParameterTypeEntity;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTo;
import com.capgemini.ccsw.codewar.challenge.to.TagTo;
import com.capgemini.ccsw.codewar.configuration.mapper.BeanMapper;
import com.capgemini.ccsw.codewar.user.UserService;

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

   @RequestMapping(path = "/", method = RequestMethod.GET)
   public List<ChallengeTo> find() {

      return this.challengeService.find();

   }

   @RequestMapping(path = "/{id}", method = RequestMethod.GET)
   public ChallengeSaveTo get(@PathVariable("id") long id) {

      return this.challengeService.get(id);

   }

   @RequestMapping(path = "/{id}/", method = RequestMethod.POST)
   public ChallengeSaveTo saveOrUpdate(@PathParam("id") Long id, @RequestBody ChallengeSaveTo challenge) {

      return this.challengeService.saveOrUpdate(id, challenge);
   }

   @RequestMapping(path = "/", method = RequestMethod.POST)
   public ChallengeSaveTo saveOrUpdate(@RequestBody ChallengeSaveTo challenge) {

      return this.saveOrUpdate(null, challenge);
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