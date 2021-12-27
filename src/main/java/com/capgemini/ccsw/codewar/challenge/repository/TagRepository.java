package com.capgemini.ccsw.codewar.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.challenge.model.TagEntity;

public interface TagRepository extends CrudRepository<TagEntity, Long> {

}