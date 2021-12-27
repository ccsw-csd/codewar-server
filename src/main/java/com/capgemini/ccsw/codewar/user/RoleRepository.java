package com.capgemini.ccsw.codewar.user;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.user.model.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

  RoleEntity findByCode(String code);

}