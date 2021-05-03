package com.capgemini.coedevon.codewar.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.coedevon.codewar.challenge.model.TagEntity;

public interface TagRepository extends CrudRepository<TagEntity, Long> {

}