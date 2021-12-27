package com.capgemini.ccsw.codewar.user;

import java.util.Date;
import java.util.Objects;

import net.sf.mmm.util.exception.api.ObjectNotFoundUserException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.codewar.user.model.UserDto;
import com.capgemini.ccsw.codewar.user.model.UserEntity;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

  /** @see #getUserDao() */
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public UserEntity get(long id) {

    return this.userRepository.findById(id).orElse(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserEntity getByUsername(String username) {

    return this.userRepository.findByUsername(username);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserEntity saveOrUpdateUser(UserDto userTo) {

    Objects.requireNonNull(userTo, "user");

    UserEntity user = null;
    if (userTo.getId() != null)
      user = get(userTo.getId());

    if (user == null) {
      user = new UserEntity();
    }

    BeanUtils.copyProperties(userTo, user, "id", "role");

    if (user.getRole() == null) {
      user.setRole(this.roleRepository.findByCode("DEVELOP"));
    }

    if (user.getDateCreation() == null)
      user.setDateCreation(new Date());

    return this.userRepository.save(user);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean delete(long id) {

    try {
      this.userRepository.deleteById(id);
      return true;
    } catch (ObjectNotFoundUserException e) {
      LOG.debug("The user with id '{}' was not found. Delete aborted.", id);
      return false;
    }
  }

}
