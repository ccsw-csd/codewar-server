package com.capgemini.ccsw.codewar.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.codewar.user.model.UserDto;

@SpringBootTest
@Transactional
public class UserControllerTest {

   @Autowired
   UserController controller;

   @Test
   public void contextLoads() throws Exception {
      assertThat(controller).isNotNull();
   }

   @Test
   public void findListUserMustReturnUsersOtherThanDevelopRole() {
      List<UserDto> users = controller.findList();

      assertThat(users).isNotNull();
      assertEquals(2, users.size());
      assertEquals("admin", users.get(0).getUsername());
      assertEquals("gestor", users.get(1).getUsername());
   }

   @Test
   public void findExistsFilterUserMustReturnThisUser() {
      List<UserDto> users = controller.findByFilter("us");

      assertThat(users).isNotNull();
      assertEquals(1, users.size());
      assertEquals("user", users.get(0).getUsername());
   }

   @Test
   public void findNotExistsFilterUserMustReturnEmpty() {
      List<UserDto> users = controller.findByFilter("notExists");

      assertThat(users).isNotNull();
      assertEquals(0, users.size());
   }

   @Test
   public void findExistsUserByUsernameMustReturnThisUser() {
      UserDto user = controller.getUserByUsername("user");

      assertThat(user).isNotNull();
      assertEquals("user", user.getUsername());
   }

   @Test
   public void findNotExistsUserByUsernameMustReturnNull() {
      UserDto user = controller.getUserByUsername("notExists");

      assertThat(user).isNull();
   }

   @Test
   public void updateRoleUserMustChangeUserRole() {

      String username = "user";
      String role = "ADMIN";

      UserDto userPrevious = controller.getUserByUsername(username);
      controller.updateUserRole(username, role);
      UserDto userActual = controller.getUserByUsername(username);

      assertThat(userPrevious).isNotNull();
      assertThat(userActual).isNotNull();

      assertNotEquals(userActual.getRole(), userPrevious.getRole());
   }

   @Test
   public void updateUserMustPersistUserData() {

      String username = "user";
      String newUsername = "NewUsername";

      UserDto userPrevious = controller.getUserByUsername(username);

      UserDto dto = controller.getUserByUsername(username);
      dto.setDateCreation(new Date());
      dto.setFirstName("NewName");
      dto.setLastName("NewLastName");
      dto.setMail("NewMail");
      dto.setUsername(newUsername);
      controller.saveOrUpdateUser(dto);

      UserDto userActual = controller.getUserByUsername(newUsername);

      assertThat(userPrevious).isNotNull();
      assertThat(userActual).isNotNull();

      assertNotEquals(userActual.getDateCreation(), userPrevious.getDateCreation());
      assertNotEquals(userActual.getFirstName(), userPrevious.getFirstName());
      assertNotEquals(userActual.getLastName(), userPrevious.getLastName());
      assertNotEquals(userActual.getMail(), userPrevious.getMail());
      assertNotEquals(userActual.getUsername(), userPrevious.getUsername());
   }

   @Test
   public void newUserMustCreateUserData() {

      String username = "NewUsername";

      UserDto dto = new UserDto();
      dto.setFirstName("NewName");
      dto.setLastName("NewLastName");
      dto.setMail("NewMail");
      dto.setUsername(username);
      controller.saveOrUpdateUser(dto);

      UserDto user = controller.getUserByUsername(username);

      assertThat(user).isNotNull();
      assertThat(user.getDateCreation()).isNotNull();
      assertEquals("Programador", user.getRole());

      assertEquals(dto.getFirstName(), user.getFirstName());
      assertEquals(dto.getLastName(), user.getLastName());
      assertEquals(dto.getMail(), user.getMail());
      assertEquals(dto.getUsername(), user.getUsername());
   }

}
