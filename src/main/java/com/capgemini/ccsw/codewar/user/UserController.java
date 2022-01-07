package com.capgemini.ccsw.codewar.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
 * The service implementation for REST calls in order to execute the logic of component {@link UserService}.
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
   *
   * @return
   */
  @RequestMapping(path = "/", method = RequestMethod.GET)
  public UserDto get() {

    UserInfoAppDto userDetails = UserUtils.getUserDetails();

    return this.beanMapper.map(userDetails, UserDto.class);

  }

  /**
   * Recupera la lista de usuarios
   *
   * @return
   */
  @RequestMapping(path = "/list", method = RequestMethod.GET)
  public List<UserDto> getList() {

    return this.userService.getList();
  }

  /**
   * Recupera usuarios filtrados por username o firstName + lastName
   *
   * @param filter
   * @return
   */
  @RequestMapping(path = "/{filter}", method = RequestMethod.GET)
  public List<UserDto> getByFilter(@PathVariable String filter) {

    return this.userService.getByFilter(filter);
  }

  /**
   * Guarda o actualiza un usuario
   *
   * @param user
   * @return
   */
  @RequestMapping(path = "/", method = RequestMethod.POST)
  public UserDto saveOrUpdateUser(@RequestBody UserDto user) {

    UserEntity entity = this.userService.saveOrUpdateUser(user);
    return this.beanMapper.map(entity, UserDto.class);
  }

  /**
   * Recupera un usuario mediante su username
   *
   * @param username
   * @return
   */
  @RequestMapping(path = "/get/{username}", method = RequestMethod.GET)
  public UserDto getUserByUsername(@PathVariable String username) {

    return this.beanMapper.map(this.userService.getByUsername(username), UserDto.class);
  }

  /**
   * Actualiza el rol de un usuario
   *
   * @param user
   * @return
   */
  @RequestMapping(path = "/role", method = RequestMethod.POST)
  public UserDto updateUserRole(@RequestBody UserDto user) {

    UserEntity entity = this.userService.updateUserRole(user);
    return this.beanMapper.map(entity, UserDto.class);
  }

}