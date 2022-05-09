package com.ccsw.codewar.challenge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.codewar.challenge.model.ChallengeEntity;
import com.ccsw.codewar.challenge.model.ChallengeParameterEntity;
import com.ccsw.codewar.challenge.model.ChallengeTagEntity;
import com.ccsw.codewar.challenge.model.ChallengeTestEntity;
import com.ccsw.codewar.challenge.model.ChallengeTestValueEntity;
import com.ccsw.codewar.challenge.repository.ChallengeParameterRepository;
import com.ccsw.codewar.challenge.repository.ChallengeTagRepository;
import com.ccsw.codewar.challenge.repository.ChallengeTestRepository;
import com.ccsw.codewar.challenge.repository.ChallengeTestValueRepository;
import com.ccsw.codewar.challenge.to.ChallengeParameterTo;
import com.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.ccsw.codewar.challenge.to.ChallengeTestTo;
import com.ccsw.codewar.challenge.to.ChallengeTestValueTo;
import com.ccsw.codewar.master.MasterService;
import com.ccsw.codewar.master.model.ParameterTypeEntity;
import com.ccsw.codewar.master.model.TagEntity;
import com.ccsw.codewar.master.to.TagTo;

@Service
@Transactional
public class ChallengeSaverImpl implements ChallengeSaver {

   @Autowired
   private MasterService masterService;

   @Autowired
   private ChallengeParameterRepository challengeParameterRepository;

   @Autowired
   private ChallengeTestRepository challengeTestRepository;

   @Autowired
   private ChallengeTestValueRepository challengeTestValueRepository;

   @Autowired
   private ChallengeTagRepository challengeTagRepository;

   /**
    * {@inheritDoc}
    */
   @Override
   public void removeChallengeData(Long id) {

      challengeTestValueRepository.deleteByChallengeId(id);
      challengeTestRepository.deleteByChallengeId(id);
      challengeParameterRepository.deleteByChallengeId(id);
      challengeTagRepository.deleteByChallengeId(id);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void saveTags(ChallengeEntity challenge, ChallengeSaveTo challengeTo) {

      List<TagTo> tags = challengeTo.getTags();

      for (TagTo tagTo : tags) {

         TagEntity tag = this.masterService.getTagByCode(tagTo.getCode()).orElse(null);

         if (tag != null) {
            ChallengeTagEntity challengeTag = new ChallengeTagEntity();
            challengeTag.setChallenge(challenge);
            challengeTag.setTag(tag);

            this.challengeTagRepository.save(challengeTag);
         }
      }
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void saveParametersAndTests(ChallengeEntity challenge, ChallengeSaveTo challengeTo) {

      int order = 0;
      List<ChallengeParameterEntity> parameterList = new ArrayList<>();

      ChallengeParameterEntity parameterEntity = saveParameterChallenge(challenge, challengeTo.getOutParameter(), order++);
      parameterList.add(parameterEntity);

      for (ChallengeParameterTo parameterTo : challengeTo.getInParameter()) {
         parameterEntity = saveParameterChallenge(challenge, parameterTo, order++);
         parameterList.add(parameterEntity);
      }

      saveTests(challenge, challengeTo, parameterList);
   }

   private ChallengeParameterEntity saveParameterChallenge(ChallengeEntity challenge, ChallengeParameterTo parameterTo, Integer order) {
      ParameterTypeEntity parameterType = masterService.getParameterTypeByCode(parameterTo.getType()).orElse(null);

      ChallengeParameterEntity challengeParameter = new ChallengeParameterEntity();
      challengeParameter.setChallenge(challenge);
      challengeParameter.setType(parameterType);
      challengeParameter.setName(parameterTo.getName());
      challengeParameter.setOrder(order);
      challengeParameter.setInput(order.intValue() > 0);
      challengeParameter = this.challengeParameterRepository.save(challengeParameter);
      return challengeParameter;
   }

   private void saveTests(ChallengeEntity challenge, ChallengeSaveTo challengeTo, List<ChallengeParameterEntity> parameters) {

      int order = 0;

      for (ChallengeTestTo challengeTestTo : challengeTo.getTest()) {

         ChallengeTestEntity challengeTest = new ChallengeTestEntity();
         challengeTest.setChallenge(challenge);
         challengeTest.setName(challengeTestTo.getName());
         challengeTest.setOrder(order++);
         challengeTest.setMaxTime(challengeTestTo.getTimeout());
         challengeTest.setPerformance(challengeTestTo.getTimeout() != null);
         challengeTest.setVisible(challengeTestTo.getVisible());

         challengeTest = this.challengeTestRepository.save(challengeTest);

         saveValueTests(challengeTest, challengeTestTo, parameters);
      }

   }

   private void saveValueTests(ChallengeTestEntity challengeTest, ChallengeTestTo challengeTestTo, List<ChallengeParameterEntity> parameters) {

      int order = 0;

      ChallengeParameterEntity parameter = parameters.get(order++);
      saveOneValueTest(challengeTest, parameter, challengeTestTo.getValueOut().getValue());

      for (ChallengeTestValueTo testValueTo : challengeTestTo.getValueIn()) {
         parameter = parameters.get(order++);
         saveOneValueTest(challengeTest, parameter, testValueTo.getValue());
      }

   }

   private void saveOneValueTest(ChallengeTestEntity challengeTest, ChallengeParameterEntity parameter, String value) {
      ChallengeTestValueEntity testValue = new ChallengeTestValueEntity();

      testValue.setTest(challengeTest);
      testValue.setParameter(parameter);
      testValue.setValue(value);

      this.challengeTestValueRepository.save(testValue);
   }

}
