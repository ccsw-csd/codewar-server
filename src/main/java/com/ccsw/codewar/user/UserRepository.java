package com.ccsw.codewar.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.codewar.user.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

  UserEntity findByUsername(String username);

  @Query(value = "select * from user where concat(first_name, ' ', last_name, ' ', username) LIKE %:name% and role = 3 LIMIT 15", nativeQuery = true)
  public List<UserEntity> findByFilter(@Param("name") String name);

  @Query(value = "select * from user where role != 3", nativeQuery = true)
  public List<UserEntity> findList();

  @Override
  List<UserEntity> findAll();

}