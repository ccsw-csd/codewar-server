package com.capgemini.coedevon.codewar.configuration.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * Can process a specific
 * {@link org.springframework.security.core.Authentication} implementation.
 *
 */
@Named
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

  @Inject
  private JsonWebTokenUtility jwtUtility;

  /**
   * {@inheritDoc}
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    Authentication authenticatedUser = null;

    String tokenHeader = (String) authentication.getPrincipal();

    tokenHeader = tokenHeader.substring(tokenHeader.indexOf(' ') + 1);

    UserInfoAppDto details = this.jwtUtility.createUserDetails(tokenHeader);
    if (details != null) {
      authenticatedUser = new JsonWebTokenAuthentication(details, createAuthorities(details), tokenHeader);
    }

    return authenticatedUser;
  }

  /**
   * @param details
   * @return
   */
  private List<GrantedAuthority> createAuthorities(UserInfoAppDto details) {

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new GrantedAuthority() {
      private static final long serialVersionUID = 1L;

      @Override
      public String getAuthority() {

        return details.getRole();
      }
    });
    return authorities;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean supports(Class<?> authentication) {

    return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
        || authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
  }

}
