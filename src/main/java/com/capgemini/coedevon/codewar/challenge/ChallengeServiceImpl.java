package com.capgemini.coedevon.codewar.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.coedevon.codewar.challenge.model.ChallengeEntity;
import com.capgemini.coedevon.codewar.challenge.model.ChallengeParameterEntity;
import com.capgemini.coedevon.codewar.challenge.model.ChallengeStatusEntity;
import com.capgemini.coedevon.codewar.challenge.model.ChallengeTagEntity;
import com.capgemini.coedevon.codewar.challenge.model.ChallengeTestEntity;
import com.capgemini.coedevon.codewar.challenge.model.ChallengeTestValueEntity;
import com.capgemini.coedevon.codewar.challenge.model.ParameterTypeEntity;
import com.capgemini.coedevon.codewar.challenge.model.TagEntity;
import com.capgemini.coedevon.codewar.challenge.repository.ChallengeParameterRepository;
import com.capgemini.coedevon.codewar.challenge.repository.ChallengeRepository;
import com.capgemini.coedevon.codewar.challenge.repository.ChallengeStatusRepository;
import com.capgemini.coedevon.codewar.challenge.repository.ChallengeTagRepository;
import com.capgemini.coedevon.codewar.challenge.repository.ChallengeTestRepository;
import com.capgemini.coedevon.codewar.challenge.repository.ChallengeTestValueRepository;
import com.capgemini.coedevon.codewar.challenge.repository.ParameterTypeRepository;
import com.capgemini.coedevon.codewar.challenge.repository.TagRepository;
import com.capgemini.coedevon.codewar.challenge.to.ChallengeParameterTo;
import com.capgemini.coedevon.codewar.challenge.to.ChallengeTestTo;
import com.capgemini.coedevon.codewar.challenge.to.ChallengeTestValueTo;
import com.capgemini.coedevon.codewar.challenge.to.ChallengeTo;
import com.capgemini.coedevon.codewar.challenge.to.TagTo;
import com.capgemini.coedevon.codewar.configuration.mapper.BeanMapper;
import com.capgemini.coedevon.codewar.configuration.security.UserUtils;
import com.capgemini.coedevon.codewar.user.UserService;

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
  public ChallengeTo get(long id) {

    ChallengeEntity challengeEntity = this.challengeRepository.findById(id).orElse(null);
    if (challengeEntity == null)
      return new ChallengeTo();

    ChallengeTo challenge = this.beanMapper.map(challengeEntity, ChallengeTo.class);

    challenge.setParameters(findParametersByChallengeId(id));
    challenge.setTags(findTagsByChallengeId(id));
    challenge.setTests(findTestsByChallengeId(id));

    return challenge;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChallengeTo saveOrUpdateUser(ChallengeTo challengeTo) {

    ChallengeEntity challenge = new ChallengeEntity();

    if (challengeTo.getId() != null) {
      removeChallengeData(challengeTo.getId());
      challenge = this.challengeRepository.findById(challengeTo.getId()).orElse(new ChallengeEntity());
    }

    BeanUtils.copyProperties(challengeTo, challenge, new String[] { "status", "user" });
    challenge.setStatus(this.challengeStatusRepository.findById(challengeTo.getStatus().getId()).orElse(null));
    challenge.setUser(this.userService.get(UserUtils.getUserDetails().getId()));

    challenge = this.challengeRepository.save(challenge);

    saveTags(challenge, challengeTo.getTags());
    saveParameters(challenge, challengeTo.getParameters(), challengeTo.getTests());
    saveTests(challenge, challengeTo.getTests());

    return get(challenge.getId());
  }

  /**
   * @param challenge
   */
  private void remapParameterId(List<ChallengeTestTo> tests, Long oldParameterId, Long newParameterId) {

    if (oldParameterId == null)
      return;

    for (ChallengeTestTo test : tests) {
      for (ChallengeTestValueTo testValue : test.getValues()) {

        if (testValue.getParameter().getId().equals(oldParameterId)) {
          testValue.getParameter().setId(newParameterId);
        }
      }
    }
  }

  /**
   * @param challenge
   * @param tests
   */
  private void saveTests(ChallengeEntity challenge, List<ChallengeTestTo> tests) {

    for (ChallengeTestTo challengeTestTo : tests) {

      ChallengeTestEntity challengeTest = new ChallengeTestEntity();
      BeanUtils.copyProperties(challengeTestTo, challengeTest, new String[] { "challenge", "id" });
      challengeTest.setChallenge(challenge);

      challengeTest = this.challengeTestRepository.save(challengeTest);

      saveValueTests(challengeTest, challengeTestTo.getValues());
    }

  }

  /**
   * @param challengeTest
   * @param values
   */
  private void saveValueTests(ChallengeTestEntity challengeTest, List<ChallengeTestValueTo> values) {

    for (ChallengeTestValueTo testValueTo : values) {

      ChallengeTestValueEntity testValue = new ChallengeTestValueEntity();
      BeanUtils.copyProperties(testValueTo, testValue, new String[] { "test", "parameter", "id" });
      testValue.setTest(challengeTest);

      Long parameterId = testValueTo.getParameter().getId();
      ChallengeParameterEntity parameter = this.challengeParameterRepository.findById(parameterId).orElse(null);
      testValue.setParameter(parameter);

      this.challengeTestValueRepository.save(testValue);
    }

  }

  /**
   * @param challenge
   * @param parameters
   */
  private List<ChallengeParameterEntity> saveParameters(ChallengeEntity challenge,
      List<ChallengeParameterTo> parameters, List<ChallengeTestTo> tests) {

    List<ChallengeParameterEntity> parameterList = new ArrayList<>();

    for (ChallengeParameterTo challengeParameterTo : parameters) {

      Long parameterTypeId = challengeParameterTo.getType().getId();
      ParameterTypeEntity parameterType = this.parameterTypeRepository.findById(parameterTypeId).orElse(null);

      ChallengeParameterEntity challengeParameter = new ChallengeParameterEntity();
      BeanUtils.copyProperties(challengeParameterTo, challengeParameter, new String[] { "challenge", "type", "id" });
      challengeParameter.setChallenge(challenge);
      challengeParameter.setType(parameterType);

      challengeParameter = this.challengeParameterRepository.save(challengeParameter);
      parameterList.add(challengeParameter);

      Long oldParameterId = challengeParameterTo.getId();
      Long newParameterId = challengeParameter.getId();
      remapParameterId(tests, oldParameterId, newParameterId);
    }

    return parameterList;
  }

  /**
   * @param challengeTo
   */
  private void saveTags(ChallengeEntity challenge, List<TagTo> tags) {

    for (TagTo tagTo : tags) {

      TagEntity tag = this.tagRepository.findById(tagTo.getId()).orElse(null);

      ChallengeTagEntity challengeTag = new ChallengeTagEntity();
      challengeTag.setChallenge(challenge);
      challengeTag.setTag(tag);

      this.challengeTagRepository.save(challengeTag);
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

    return StreamSupport.stream(this.parameterTypeRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ChallengeStatusEntity> findAllChallengeStatus() {

    return StreamSupport.stream(this.challengeStatusRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
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
      test.setValues(findTestValuesByTestId(test.getId()));
      tests.add(test);
    }

    return tests;
  }

  /**
   * @param id
   * @return
   */
  private List<ChallengeTestValueTo> findTestValuesByTestId(long id) {

    List<ChallengeTestValueEntity> list = this.challengeTestValueRepository.findByTest_idOrderByParameter_Order(id);

    return this.beanMapper.mapList(list, ChallengeTestValueTo.class);
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
  private List<ChallengeParameterTo> findParametersByChallengeId(long id) {

    List<ChallengeParameterEntity> list = this.challengeParameterRepository.findByChallenge_IdOrderByOrder(id);
    return this.beanMapper.mapList(list, ChallengeParameterTo.class);
  }

  @Override
  public List<ChallengeTo> find() {

    Long id = UserUtils.getUserDetails().getId();
    return this.challengeRepository.findChallengesWithParticipationNum(id);

  }

}
