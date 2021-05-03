package com.capgemini.coedevon.codewar.user;

import com.capgemini.coedevon.codewar.user.model.UserDto;
import com.capgemini.coedevon.codewar.user.model.UserEntity;

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
   * Saves or updates a user and store it in the database.
   *
   * @param user the {@link UserDto} to create/update.
   * @return the new {@link UserEntity} that has been saved/updated with the Id.
   */
  UserEntity saveOrUpdateUser(UserDto user);

  /**
   * Deletes a user from the database by its id 'userId'.
   *
   * @param id Id of the user to delete
   * @return boolean <code>true</code> if the user can be deleted,
   *         <code>false</code> otherwise
   */
  boolean delete(long id);

}
