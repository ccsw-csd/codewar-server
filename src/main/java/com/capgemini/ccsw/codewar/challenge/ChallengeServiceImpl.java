package com.capgemini.ccsw.codewar.challenge;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeEntity;
import com.capgemini.ccsw.codewar.challenge.model.ChallengeParameterEntity;
import com.capgemini.ccsw.codewar.challenge.model.ChallengeStatusEntity;
import com.capgemini.ccsw.codewar.challenge.model.ChallengeTagEntity;
import com.capgemini.ccsw.codewar.challenge.model.ChallengeTestEntity;
import com.capgemini.ccsw.codewar.challenge.model.ChallengeTestValueEntity;
import com.capgemini.ccsw.codewar.challenge.model.ParameterTypeEntity;
import com.capgemini.ccsw.codewar.challenge.model.TagEntity;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeParameterRepository;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeRepository;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeStatusRepository;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeTagRepository;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeTestRepository;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeTestValueRepository;
import com.capgemini.ccsw.codewar.challenge.repository.ParameterTypeRepository;
import com.capgemini.ccsw.codewar.challenge.repository.TagRepository;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeParameterTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTestValueTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeTo;
import com.capgemini.ccsw.codewar.challenge.to.TagTo;
import com.capgemini.ccsw.codewar.configuration.mapper.BeanMapper;
import com.capgemini.ccsw.codewar.configuration.security.UserUtils;
import com.capgemini.ccsw.codewar.user.UserService;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

   /** Logger instance. */
   private static final Logger LOG = LoggerFactory.getLogger(ChallengeServiceImpl.class);

   @Autowired
   ChallengeRepository challengeRepository;

   @Autowired
   ChallengeParameterRepository challengeParameterRepository;

   @Autowired
   ChallengeTagRepository challengeTagRepository;

   @Autowired
   ChallengeTestRepository challengeTestRepository;

   @Autowired
   ChallengeTestValueRepository challengeTestValueRepository;

   @Autowired
   TagRepository tagRepository;

   @Autowired
   ParameterTypeRepository parameterTypeRepository;

   @Autowired
   ChallengeStatusRepository challengeStatusRepository;

   @Autowired
   UserService userService;

   @Autowired
   private BeanMapper beanMapper;

   /**
   * {@inheritDoc}
   */
   @Override
   public ChallengeSaveTo get(long id) {

      ChallengeEntity challengeEntity = this.challengeRepository.findById(id).orElse(null);
      if (challengeEntity == null)
         return new ChallengeSaveTo();

      ChallengeSaveTo challenge = this.beanMapper.map(challengeEntity, ChallengeSaveTo.class);

      challenge.setOutParameter(findOutParameterByChallengeId(id));
      challenge.setInParameter(findParametersByChallengeId(id));
      challenge.setTags(findTagsByChallengeId(id));
      challenge.setTest(findTestsByChallengeId(id));

      return challenge;
   }

   /**
   * {@inheritDoc}
   */
   @Override
   public ChallengeSaveTo saveOrUpdate(Long id, ChallengeSaveTo challengeTo) {

      ChallengeEntity challenge = new ChallengeEntity();

      if (id != null) {
         removeChallengeData(id);
         challenge = this.challengeRepository.findById(id).orElse(new ChallengeEntity());
      } //
      else {
         challenge.setStatus(challengeStatusRepository.findById(ChallengeStatusEntity.BORRADOR).get());
      }

      BeanUtils.copyProperties(challengeTo, challenge);
      challenge.setUser(this.userService.get(UserUtils.getUserDetails().getId()));
      challenge.setCreationDate(new Date());

      challenge = this.challengeRepository.save(challenge);

      saveTags(challenge, challengeTo);
      saveParametersAndTests(challenge, challengeTo);

      return get(challenge.getId());
   }

   /**
   * @param challenge
   * @param parameters
   */
   private void saveParametersAndTests(ChallengeEntity challenge, ChallengeSaveTo challengeTo) {

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

   private ChallengeParameterEntity saveParameterChallenge(ChallengeEntity challenge, ChallengeParameterTo parameterOut, Integer order) {
      ParameterTypeEntity parameterType = this.parameterTypeRepository.getByCode(parameterOut.getType());

      ChallengeParameterEntity challengeParameter = new ChallengeParameterEntity();
      challengeParameter.setChallenge(challenge);
      challengeParameter.setType(parameterType);
      challengeParameter.setName(parameterOut.getName());
      challengeParameter.setOrder(order);
      challengeParameter.setInput(order.intValue() > 0);
      challengeParameter = this.challengeParameterRepository.save(challengeParameter);
      return challengeParameter;
   }

   /**
    * @param challenge
    * @param tests
    */
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

   /**
   * @param challengeTest
   * @param values
   */
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

   /**
   * @param challengeTo
   */
   private void saveTags(ChallengeEntity challenge, ChallengeSaveTo challengeTo) {

      List<TagTo> tags = challengeTo.getTags();

      for (TagTo tagTo : tags) {

         TagEntity tag = this.tagRepository.findById(tagTo.getId()).orElse(null);

         if (tag != null) {
            ChallengeTagEntity challengeTag = new ChallengeTagEntity();
            challengeTag.setChallenge(challenge);
            challengeTag.setTag(tag);

            this.challengeTagRepository.save(challengeTag);
         }
      }

   }

   /**
   * @param id
   */
   private void removeChallengeData(Long id) {

      // TODO Auto-generated method stub

   }

   /**
   * {@inheritDoc}
   */
   @Override
   public List<TagEntity> findAllTags() {

      return StreamSupport.stream(this.tagRepository.findAll().spliterator(), false).collect(Collectors.toList());
   }

   /**
   * {@inheritDoc}
   */
   @Override
   public List<ParameterTypeEntity> findAllParameterType() {

      return StreamSupport.stream(this.parameterTypeRepository.findAll().spliterator(), false).collect(Collectors.toList());
   }

   /**
   * {@inheritDoc}
   */
   @Override
   public List<ChallengeStatusEntity> findAllChallengeStatus() {

      return StreamSupport.stream(this.challengeStatusRepository.findAll().spliterator(), false).collect(Collectors.toList());
   }

   /**
   * @param id
   * @return
   */
   private List<ChallengeTestTo> findTestsByChallengeId(long id) {

      List<ChallengeTestEntity> list = this.challengeTestRepository.findByChallenge_IdOrderByOrder(id);
      List<ChallengeTestTo> tests = new ArrayList<>();

      for (ChallengeTestEntity testEntity : list) {

         ChallengeTestTo test = this.beanMapper.map(testEntity, ChallengeTestTo.class);
         //test.setValues(findTestValuesByTestId(testEntity.getId()));
         tests.add(test);
      }

      return tests;
   }

   /**
   * @param id
   * @return
   */
   private List<TagTo> findTagsByChallengeId(long id) {

      List<ChallengeTagEntity> list = this.challengeTagRepository.findByChallenge_Id(id);
      List<TagTo> tags = new ArrayList<>();

      for (ChallengeTagEntity entity : list) {
         tags.add(this.beanMapper.map(entity.getTag(), TagTo.class));
      }

      return tags;
   }

   /**
   * @param id
   * @return
   */
   private ChallengeParameterTo findOutParameterByChallengeId(long id) {

      List<ChallengeParameterEntity> list = this.challengeParameterRepository.findByChallenge_IdOrderByOrder(id);

      if (list == null || list.size() == 0)
         return null;

      ChallengeParameterEntity parameter = list.get(0);
      return new ChallengeParameterTo(parameter.getType() != null ? parameter.getType().getCode() : null, parameter.getName());

   }

   /**
   * @param id
   * @return
   */
   private List<ChallengeParameterTo> findParametersByChallengeId(long id) {

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

   @Override
   public List<ChallengeTo> find() {

      Long id = UserUtils.getUserDetails().getId();
      return this.challengeRepository.findChallengesWithParticipationNum(id);

   }

}
