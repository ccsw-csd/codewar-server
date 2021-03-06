package com.ccsw.codewar.master.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.codewar.master.model.StatusEntity;

public interface StatusRepository extends CrudRepository<StatusEntity, Long> {

   Optional<StatusEntity> getByCode(String code);

}