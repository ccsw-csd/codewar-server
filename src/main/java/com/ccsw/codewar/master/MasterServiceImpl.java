package com.ccsw.codewar.master;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.codewar.master.model.StatusEntity;
import com.ccsw.codewar.master.model.ParameterTypeEntity;
import com.ccsw.codewar.master.model.TagEntity;
import com.ccsw.codewar.master.repository.StatusRepository;
import com.ccsw.codewar.master.repository.ParameterTypeRepository;
import com.ccsw.codewar.master.repository.TagRepository;

@Service
@Transactional
public class MasterServiceImpl implements MasterService {

   /** Logger instance. */
   private static final Logger LOG = LoggerFactory.getLogger(MasterServiceImpl.class);

   @Autowired
   TagRepository tagRepository;

   @Autowired
   ParameterTypeRepository parameterTypeRepository;

   @Autowired
   StatusRepository challengeStatusRepository;

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

   @Override
   public Optional<StatusEntity> getStatusByCode(String statusCode) {

      return challengeStatusRepository.getByCode(statusCode);
   }

   @Override
   public Optional<ParameterTypeEntity> getParameterTypeByCode(String typeCode) {
      return parameterTypeRepository.getByCode(typeCode);
   }

   @Override
   public Optional<TagEntity> getTagByCode(String tagCode) {
      return tagRepository.getByCode(tagCode);
   }
}
