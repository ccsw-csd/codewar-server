package com.capgemini.ccsw.codewar.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.codewar.configuration.mapper.BeanMapper;
import com.capgemini.ccsw.codewar.configuration.security.UserInfoAppDto;
import com.capgemini.ccsw.codewar.configuration.security.UserUtils;
import com.capgemini.ccsw.codewar.user.model.UserDto;
import com.capgemini.ccsw.codewar.user.model.UserEntity;

/**
 * The service implementation for REST calls in order to execute the logic of
 * component {@link UserService}.
 *
 * @author rroigped
 */
@RequestMapping(value = "/user/")
@RestController
public class UserController {

  @Autowired
  private BeanMapper beanMapper;

  @Autowired
  private UserService userService;

  /**
   * Recupera el usuario logado
   * @return
   */
  @RequestMapping(path = "/", method = RequestMethod.GET)
  public UserDto get() {

    UserInfoAppDto userDetails = UserUtils.getUserDetails();

    return this.beanMapper.map(userDetails, UserDto.class);

  }

  @RequestMapping(path = "/", method = RequestMethod.POST)
  public UserDto saveOrUpdateUser(@RequestBody UserDto user) {

    UserEntity entity = this.userService.saveOrUpdateUser(user);
    return this.beanMapper.map(entity, UserDto.class);
  }

}