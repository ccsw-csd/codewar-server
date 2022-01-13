package com.capgemini.ccsw.codewar.challenge.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.codewar.challenge.model.ChallengeEntity;
import com.capgemini.ccsw.codewar.challenge.repository.ChallengeRepository;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeActivateResponseTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeItemListTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeMinimalTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.capgemini.ccsw.codewar.configuration.mapper.BeanMapper;
import com.capgemini.ccsw.codewar.configuration.security.UserUtils;
import com.capgemini.ccsw.codewar.master.MasterService;
import com.capgemini.ccsw.codewar.master.model.StatusEntity;
import com.capgemini.ccsw.codewar.user.UserService;

@Service
@Transactional
public class ChallengeImpl implements Challenge {

   /** Logger instance. */
   private static final Logger LOG = LoggerFactory.getLogger(ChallengeImpl.class);

   @Autowired
   private ChallengeRepository challengeRepository;

   @Autowired
   private UserService userService;

   @Autowired
   private MasterService masterService;

   @Autowired
   private ChallengeMapper challengeMapper;

   @Autowired
   private ChallengeSaver challengeSaver;

   @Autowired
   private ChallengeValidator challengeValidator;

   @Autowired
   private BeanMapper beanMapper;

   /**
   * {@inheritDoc}
   */
   @Override
   public List<ChallengeItemListTo> find() {

      return this.challengeRepository.findChallengesWithParticipationNum();

   }

   /**
   * {@inheritDoc}
   */
   @Override
   public List<ChallengeItemListTo> findActiveChallenges() {

      return this.challengeRepository.findActiveChallenges();

   }

   /**
   * {@inheritDoc}
   */
   @Override
   public ChallengeSaveTo get(long id) {

      ChallengeEntity challengeEntity = this.challengeRepository.findById(id).orElse(null);
      if (challengeEntity == null)
         return new ChallengeSaveTo();

      ChallengeSaveTo challenge = this.beanMapper.map(challengeEntity, ChallengeSaveTo.class);

      challenge.setStatusCode(challengeEntity.getStatus().getCode());
      challenge.setOutParameter(this.challengeMapper.findOutParameterByChallengeId(id));
      challenge.setInParameter(this.challengeMapper.findParametersByChallengeId(id));
      challenge.setTags(this.challengeMapper.findTagsByChallengeId(id));
      challenge.setTest(this.challengeMapper.findTestsByChallengeId(id));

      return challenge;
   }

   /**
   * {@inheritDoc}
   */
   @Override
   public ChallengeSaveTo saveOrUpdate(Long id, ChallengeSaveTo challengeTo) {

      ChallengeEntity challenge = new ChallengeEntity();

      if (id != null) {
         this.challengeSaver.removeChallengeData(id);
         challenge = this.challengeRepository.findById(id).orElse(new ChallengeEntity());
      } //
      else {
         challenge.setStatus(this.masterService.getStatusByCode(StatusEntity.BORRADOR).get());
      }

      BeanUtils.copyProperties(challengeTo, challenge);
      challenge.setUser(this.userService.get(UserUtils.getUserDetails().getId()));
      challenge.setCreationDate(new Date());

      challenge = this.challengeRepository.save(challenge);

      this.challengeSaver.saveTags(challenge, challengeTo);
      this.challengeSaver.saveParametersAndTests(challenge, challengeTo);

      return get(challenge.getId());
   }

   /**
   * {@inheritDoc}
   */
   @Override
   public void delete(Long id) {

      this.challengeSaver.removeChallengeData(id);
      this.challengeRepository.deleteById(id);
   }

   /**
   * {@inheritDoc}
   */
   @Override
   public ChallengeActivateResponseTo check(Long id) {

      ChallengeSaveTo challenge = get(id);
      return this.challengeValidator.check(challenge);

   }

   /**
   * {@inheritDoc}
   */
   @Override
   public ChallengeActivateResponseTo checkAndActivate(Long id) {

      ChallengeSaveTo challengeTo = get(id);
      ChallengeActivateResponseTo checkData = this.challengeValidator.check(challengeTo);

      if (checkData.isValid()) {
         ChallengeEntity challenge = this.challengeRepository.findById(id).get();

         challenge.setStatus(this.masterService.getStatusByCode(StatusEntity.ACTIVADO).get());
         challenge.setStartDate(new Date());
         challenge.setBaseCode(this.challengeValidator.generateBaseCode(challengeTo));

         this.challengeRepository.save(challenge);
      }

      return checkData;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public ChallengeMinimalTo getMinimal(long id) {
      return challengeRepository.getChallengeMinimal(id);
   }

}
