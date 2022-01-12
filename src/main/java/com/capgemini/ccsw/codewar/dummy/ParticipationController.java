package com.capgemini.ccsw.codewar.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.codewar.configuration.security.UserInfoAppDto;
import com.capgemini.ccsw.codewar.configuration.security.UserUtils;
import com.capgemini.ccsw.codewar.dummy.service.Participation;
import com.capgemini.ccsw.codewar.dummy.to.ChallengeParticipationExecutionTo;
import com.capgemini.ccsw.codewar.dummy.to.ChallengeParticipationTo;
import com.capgemini.ccsw.codewar.dummy.to.CodeInTo;

@RequestMapping(value = "/participation/")
@RestController
public class ParticipationController {

   @Autowired
   private Participation participationService;

   @RequestMapping(path = "/challenge/{id}", method = RequestMethod.GET)
   public ChallengeParticipationTo getChallengeParticipation(@PathVariable("id") long id) {
      UserInfoAppDto userDetails = UserUtils.getUserDetails();

      return this.participationService.getChallengeParticipation(id, userDetails.getUsername());
   }

   @RequestMapping(path = "/challenge/{id}/execute", method = RequestMethod.POST)
   public ChallengeParticipationExecutionTo executeChallengeParticipation(@PathVariable("id") long id, @RequestBody CodeInTo codeIn) {

      return this.participationService.executeChallengeParticipation(id, codeIn.getCode());
   }

}
