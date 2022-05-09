package com.ccsw.codewar.master.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.codewar.master.model.TagEntity;

public interface TagRepository extends CrudRepository<TagEntity, Long> {

   Optional<TagEntity> getByCode(String code);

}