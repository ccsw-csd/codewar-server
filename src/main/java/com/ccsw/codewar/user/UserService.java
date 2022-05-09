package com.ccsw.codewar.user;

import java.util.List;

import com.ccsw.codewar.user.model.UserDto;
import com.ccsw.codewar.user.model.UserEntity;

public interface UserService {

  /**
   * Get user by id
   *
   * @param id userId
   * @return a {UserEntity}
   */
  UserEntity get(long id);

  /**
   * Get user by username
   *
   * @param username username
   * @return a {UserEntity}
   */
  UserEntity getByUsername(String username);

  /**
   * Get user by Filter
   *
   * @return a {List<UserDto>}
   */
  List<UserDto> findByFilter(String filter);

  /**
   * Get a list of users
   *
   * @return a {List<UserDto>}
   */
  List<UserDto> findList();

  /**
   * Saves or updates a user and store it in the database.
   *
   * @param user the {@link UserDto} to create/update.
   * @return the new {@link UserEntity} that has been saved/updated with the Id.
   */
  UserEntity saveOrUpdateUser(UserDto user);

  /**
   * Update a user's role and store it in the database.
   *
   * @param user the {@link UserDto} to update.
   * @return the new {@link UserEntity} that has been updated.
   */
  UserEntity updateUserRole(String username, String role);

  /**
   * Deletes a user from the database by its id 'userId'.
   *
   * @param id Id of the user to delete
   * @return boolean <code>true</code> if the user can be deleted, <code>false</code> otherwise
   */
  boolean delete(long id);

}
