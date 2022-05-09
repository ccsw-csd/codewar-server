package com.ccsw.codewar.user;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.codewar.user.model.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

  RoleEntity findByCode(String code);

}