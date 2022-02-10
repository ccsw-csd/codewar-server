package com.capgemini.ccsw.codewar.participation.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.codewar.challenge.service.Challenge;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeMinimalTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.capgemini.ccsw.codewar.compiler.JavaCompiler;
import com.capgemini.ccsw.codewar.compiler.to.CodeDiagnosticTo;
import com.capgemini.ccsw.codewar.compiler.to.CompilerException;
import com.capgemini.ccsw.codewar.compiler.to.TestExecutionResultTo;
import com.capgemini.ccsw.codewar.configuration.security.UserUtils;
import com.capgemini.ccsw.codewar.participation.ParticipationRepository;
import com.capgemini.ccsw.codewar.participation.model.ParticipationEntity;
import com.capgemini.ccsw.codewar.participation.to.ChallengeParticipationExecutionTo;
import com.capgemini.ccsw.codewar.participation.to.ChallengeParticipationTo;
import com.capgemini.ccsw.codewar.user.UserService;

@Service
public class ParticipationImpl implements Participation {

   @Autowired
   ParticipationRepository repository;

   @Autowired
   Challenge challengeService;

   @Autowired
   UserService userService;

   @Autowired
   JavaCompiler javaCompiler;

   @Override
   public ChallengeParticipationTo getChallengeParticipation(long id, String username) {

      ChallengeMinimalTo challenge = challengeService.getMinimal(id);

      ChallengeParticipationTo challengeParticipation = new ChallengeParticipationTo();
      challengeParticipation.setChallenge(challenge);

      return challengeParticipation;
   }

   @Override
   public ChallengeParticipationExecutionTo executeChallengeParticipation(long id, String code) {

      ChallengeParticipationExecutionTo response;
      ChallengeSaveTo challenge = challengeService.get(id);

      if (challenge == null)
         return new ChallengeParticipationExecutionTo(true);

      try {
         List<CodeDiagnosticTo> compileErrors = javaCompiler.compileInMemory(code, challenge.getClassName());
         response = new ChallengeParticipationExecutionTo(compileErrors);

         if (response.isCompileError() == false) {
            List<TestExecutionResultTo> testExecutionResult = javaCompiler.compileAndExecuteTests(code, challenge, false);
            response.setTestExecutionResult(testExecutionResult);
         }

      } catch (CompilerException e) {
         e.printStackTrace();
         response = new ChallengeParticipationExecutionTo(true);
      }

      return response;

   }

   @Override
   public void sendChallengeParticipation(long id, String code) {

      ParticipationEntity participation = new ParticipationEntity();

      participation.setChallenge(challengeService.getEntity(id));
      participation.setUser(userService.getByUsername(UserUtils.getUserDetails().getUsername()));
      participation.setDate(new Date());
      participation.setCode(code);
      participation.setEvaluated(false);

      repository.save(participation);
   }

}
