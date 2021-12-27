package com.capgemini.ccsw.codewar.user;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.user.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

  UserEntity findByUsername(String username);

}