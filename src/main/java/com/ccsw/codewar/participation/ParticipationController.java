package com.ccsw.codewar.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.codewar.configuration.security.UserInfoAppDto;
import com.ccsw.codewar.configuration.security.UserUtils;
import com.ccsw.codewar.participation.service.Participation;
import com.ccsw.codewar.participation.to.ChallengeParticipationExecutionTo;
import com.ccsw.codewar.participation.to.ChallengeParticipationTo;
import com.ccsw.codewar.participation.to.CodeInTo;

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

   @RequestMapping(path = "/challenge/{id}/", method = RequestMethod.POST)
   public void sendChallengeParticipation(@PathVariable("id") long id, @RequestBody CodeInTo codeIn) {

      this.participationService.sendChallengeParticipation(id, codeIn.getCode());
   }

}
