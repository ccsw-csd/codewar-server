package com.capgemini.coedevon.codewar.user;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.coedevon.codewar.user.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

  UserEntity findByUsername(String username);

}