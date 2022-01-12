package com.capgemini.ccsw.codewar.dummy.service;

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
import com.capgemini.ccsw.codewar.dummy.to.ChallengeParticipationExecutionTo;
import com.capgemini.ccsw.codewar.dummy.to.ChallengeParticipationTo;

@Service
public class ParticipationImpl implements Participation {

   @Autowired
   Challenge challengeService;

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

}
