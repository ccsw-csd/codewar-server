package com.capgemini.ccsw.codewar.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.codewar.challenge.service.Challenge;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeActivateResponseTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeItemListTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.capgemini.ccsw.codewar.user.UserService;

/**
 * The service implementation for REST calls in order to execute the logic of component {@link UserService}.
 *
 * @author rroigped
 */
@RequestMapping(value = "/challenge/")
@RestController
public class ChallengeController {

   @Autowired
   private Challenge challengeService;

   @RequestMapping(path = "/", method = RequestMethod.GET)
   public List<ChallengeItemListTo> find() {

      return this.challengeService.find();
   }

   @RequestMapping(path = "/active/", method = RequestMethod.GET)
   public List<ChallengeItemListTo> findActiveChallenges() {

      return this.challengeService.findActiveChallenges();
   }

   @RequestMapping(path = "/{id}/", method = RequestMethod.GET)
   public ChallengeSaveTo get(@PathVariable("id") long id) {

      return this.challengeService.get(id);
   }

   @RequestMapping(path = "/{id}/", method = RequestMethod.POST)
   public ChallengeSaveTo saveOrUpdate(@PathVariable("id") Long id, @RequestBody ChallengeSaveTo challenge) {

      return this.challengeService.saveOrUpdate(id, challenge);
   }

   @RequestMapping(path = "/", method = RequestMethod.POST)
   public ChallengeSaveTo saveOrUpdate(@RequestBody ChallengeSaveTo challenge) {

      return this.saveOrUpdate(null, challenge);
   }

   @RequestMapping(path = "/{id}/", method = RequestMethod.DELETE)
   public void delete(@PathVariable("id") Long id) {

      this.challengeService.delete(id);
   }

   @RequestMapping(path = "/{id}/check/", method = RequestMethod.GET)
   public ChallengeActivateResponseTo check(@PathVariable("id") Long id) {

      return this.challengeService.check(id);
   }

   @RequestMapping(path = "/{id}/activate/", method = RequestMethod.POST)
   public ChallengeActivateResponseTo checkAndActivate(@PathVariable("id") Long id) {

      return this.challengeService.checkAndActivate(id);
   }

   @RequestMapping(path = "/{id}/finalize/", method = RequestMethod.POST)
   public void finalize(@PathVariable("id") Long id) {

      this.challengeService.finalize(id);
   }

}