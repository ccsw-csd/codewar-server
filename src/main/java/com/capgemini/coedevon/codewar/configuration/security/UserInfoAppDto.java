package com.capgemini.coedevon.codewar.configuration.security;

import java.util.Date;

import org.springframework.security.core.Authentication;

/**
 * Class to store user information which is later encapsulated into {@link Authentication} object.
 *
 */
public class UserInfoAppDto extends UserInfoDto {

  private String role;

  private String jwt;
  
  private Long id;

  private Date expiration;

  /**
   * @return role
   */
  public String getRole() {

    return this.role;
  }

  /**
   * @param role new value of {@link #getrole}.
   */
  public void setRole(String role) {

    this.role = role;
  }

  /**
   * @return expiration
   */
  public Date getExpiration() {

    return this.expiration;
  }

  /**
   * @param expiration new value of {@link #getexpiration}.
   */
  public void setExpiration(Date expiration) {

    this.expiration = expiration;
  }

  /**
   * @return id
   */
  public Long getId() {

    return id;
  }

  /**
   * @param id new value of {@link #getid}.
   */
  public void setId(Long id) {

    this.id = id;
  }

public String getJwt() {

    return jwt;
}

public void setJwt(String jwt) {

    this.jwt = jwt;
}

}
