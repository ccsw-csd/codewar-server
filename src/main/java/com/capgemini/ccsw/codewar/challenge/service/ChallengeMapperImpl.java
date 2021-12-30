package com.capgemini.ccsw.codewar.challenge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeParameterEntity;
import com.capgemini.ccsw.codewar.challenge.model.ChallengeTagEntity;
import com.capgemini.ccsw.codewar.challenge.model.ChallengeTestEntity;
import com.capgemini.ccsw.codewar.challenge.model.ChallengeTestValueEntity;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeParameterRepository;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeTagRepository;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeTestRepository;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeTestValueRepository;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeParameterTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestValueTo;
import com.capgemini.ccsw.codewar.configuration.mapper.BeanMapper;
import com.capgemini.ccsw.codewar.master.model.ParameterTypeEntity;
import com.capgemini.ccsw.codewar.master.to.TagTo;

@Service
public class ChallengeMapperImpl implements ChallengeMapper {

   @Autowired
   private ChallengeTestRepository challengeTestRepository;

   @Autowired
   private ChallengeTestValueRepository challengeTestValueRepository;

   @Autowired
   private ChallengeTagRepository challengeTagRepository;

   @Autowired
   private ChallengeParameterRepository challengeParameterRepository;

   @Autowired
   private BeanMapper beanMapper;

   /**
    * {@inheritDoc}
    */
   @Override
   public List<ChallengeTestTo> findTestsByChallengeId(long id) {

      List<ChallengeTestEntity> list = this.challengeTestRepository.findByChallenge_IdOrderByOrder(id);
      List<ChallengeTestTo> testsTo = new ArrayList<>();

      for (ChallengeTestEntity test : list) {

         ChallengeTestTo testTo = this.beanMapper.map(test, ChallengeTestTo.class);

         fillTestValues(testTo, test.getId());

         testsTo.add(testTo);
      }

      return testsTo;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public List<TagTo> findTagsByChallengeId(long id) {

      List<ChallengeTagEntity> list = this.challengeTagRepository.findByChallenge_Id(id);
      List<TagTo> tags = new ArrayList<>();

      for (ChallengeTagEntity entity : list) {
         tags.add(this.beanMapper.map(entity.getTag(), TagTo.class));
      }

      return tags;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public ChallengeParameterTo findOutParameterByChallengeId(long id) {

      List<ChallengeParameterEntity> list = this.challengeParameterRepository.findByChallenge_IdOrderByOrder(id);

      if (list == null || list.size() == 0)
         return null;

      ChallengeParameterEntity parameter = list.get(0);
      return new ChallengeParameterTo(parameter.getType() != null ? parameter.getType().getCode() : null, parameter.getName());

   }

   /**
    * {@inheritDoc}
    */
   @Override
   public List<ChallengeParameterTo> findParametersByChallengeId(long id) {

      List<ChallengeParameterEntity> list = this.challengeParameterRepository.findByChallenge_IdOrderByOrder(id);

      if (list == null || list.size() == 0)
         return null;

      list.remove(0);

      List<ChallengeParameterTo> listTo = new ArrayList<>();
      for (ChallengeParameterEntity parameter : list) {
         listTo.add(new ChallengeParameterTo(parameter.getType() != null ? parameter.getType().getCode() : null, parameter.getName()));
      }

      return listTo;
   }

   private void fillTestValues(ChallengeTestTo test, Long id) {

      List<ChallengeTestValueEntity> listTestValue = this.challengeTestValueRepository.findByTest_idOrderByParameter_Order(id);

      if (listTestValue == null || listTestValue.size() == 0)
         return;

      int order = 0;

      List<ChallengeTestValueTo> valueIn = new ArrayList<>();
      test.setValueIn(valueIn);

      for (ChallengeTestValueEntity testValue : listTestValue) {

         ChallengeTestValueTo testValueTo = convertOneTestValue(testValue);

         if (order == 0)
            test.setValueOut(testValueTo);
         else
            valueIn.add(testValueTo);

         order++;
      }
   }

   private ChallengeTestValueTo convertOneTestValue(ChallengeTestValueEntity valueOut) {
      String name = valueOut.getValue();

      ChallengeParameterEntity parameter = valueOut.getParameter();
      String parameterName = parameter != null ? parameter.getName() : "";

      ParameterTypeEntity parameterType = parameter.getType();
      String parameterTypeName = parameterType != null ? parameterType.getName() : null;

      return new ChallengeTestValueTo(name, parameterName, parameterTypeName);
   }

}
