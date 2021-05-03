package com.capgemini.coedevon.codewar.configuration.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.capgemini.coedevon.codewar.user.UserServiceImpl;
import com.capgemini.coedevon.codewar.user.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author jhcore
 * @author rroigped
 */
@Component
public class JsonWebTokenUtility {

  private static final Logger LOG = LoggerFactory.getLogger(JsonWebTokenUtility.class);

  private static final String CLAIM_FIRST_NAME = "firstName";

  private static final String CLAIM_LAST_NAME = "lastName";

  private static final String CLAIM_DISPLAY_NAME = "displayName";

  private static final String CLAIM_EMPLOYEE_NUMBER = "employeeNumber";

  private static final String CLAIM_GLOBAL_ID = "globalID";

  private static final String CLAIM_EMPLOYEE_GRADE = "employeeGrade";

  private static final String CLAIM_EMAIL = "email";

  private static final String CLAIM_OFFICE_NAME = "officeName";

  private static final String CLAIM_REFRESH = "refresh";

  private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

  private static final Long EXPIRATION_TIME = 1 * 60 * 60 * 1000L;

  private Key secretKey;

  @Autowired
  private UserServiceImpl userService;

  private Map<String, UserInfoAppDto> userCache = new HashMap<>();

  /**
   * Create JWT from UserDetails
   *
   * @param userDetails
   * @return The JWT token
   */
  public final String createAccessJWT(UserInfoDto userDetails, Date expirationDate) {

    JwtBuilder jBuilder = createJWT(userDetails, expirationDate);

    addCustomPropertiesUserDetailsToJwt(userDetails, jBuilder);

    return jBuilder.compact();

  }

  /**
   * Create JWT from UserDetails
   *
   * @param userDetails
   * @return The JWT token
   */
  public final String createRefreshJWT(UserInfoDto userDetails, Date expirationDate) {

    JwtBuilder jBuilder = createJWT(userDetails, expirationDate);
    jBuilder.claim(CLAIM_REFRESH, true);

    return jBuilder.compact();

  }

  /**
   * Create UserDetails from JWT
   *
   * @param jwtToken The json web token
   * @return userDetails
   */
  public final UserInfoAppDto createUserDetails(String jwtToken) {

    try {
      Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(jwtToken).getBody();

      if (isExpired(claims.getExpiration()))
        return null;

      String username = claims.getSubject();

      UserInfoAppDto userDetails = this.userCache.get(username);

      if (userDetails == null || isExpired(userDetails.getExpiration())) {
        LOG.info("Creamos y cacheamos el usuario: " + username);
        userDetails = createNewUserDetails(username);
        addCustomPropertiesJwtToUserDetails(claims, userDetails);
        if (userDetails.getId() != null)
          this.userCache.put(username, userDetails);
      }

      return userDetails;

    } catch (ExpiredJwtException ex) {
      LOG.info("User token expired " + jwtToken);
      return null;
    }

  }

  private UserInfoAppDto createNewUserDetails(String username) {

    UserInfoAppDto userDetails = new UserInfoAppDto();
    userDetails.setUsername(username);
    userDetails.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));

    UserEntity user = this.userService.getByUsername(username);
    if (user != null) {
      userDetails.setRole(user.getRole().getCode());
      userDetails.setId(user.getId());
    }

    return userDetails;
  }

  /**
   *
   * @param jwtToken
   * @return
   */
  public final boolean tokensAreInvalid(String accessToken, String refreshToken) {

    Claims claimsAccessToken = null;
    Claims claimsRefreshToken = null;

    try {
      claimsAccessToken = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(accessToken).getBody();

      if (isExpired(claimsAccessToken.getExpiration()))
        return true;

    } catch (ExpiredJwtException ex) {
      LOG.info("User token expired " + accessToken);
      return true;
    }

    try {
      claimsRefreshToken = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(refreshToken).getBody();

      if (isExpired(claimsRefreshToken.getExpiration()))
        return true;

    } catch (ExpiredJwtException ex) {
      LOG.info("User token expired " + refreshToken);
      return true;
    }

    Boolean isRefreshClaim = (Boolean) claimsRefreshToken.get(CLAIM_REFRESH);
    if (isRefreshClaim == false)
      return true;

    Date expirationDateAccessToken = claimsAccessToken.getExpiration();
    Date expirationDateRefreshToken = claimsRefreshToken.getExpiration();

    return expirationDateAccessToken.getTime() != expirationDateRefreshToken.getTime();
  }

  /**
   * Create JWT from UserDetails
   *
   * @param userDetails
   * @return The JWT token
   */
  private final JwtBuilder createJWT(UserInfoDto userDetails, Date expirationDate) {

    return Jwts.builder().setSubject(userDetails.getUsername()).setExpiration(expirationDate)//
        .signWith(SIGNATURE_ALGORITHM, this.secretKey);

  }

  /**
   * @param userDetails
   */
  private boolean isExpired(Date expirationDate) {

    Date now = new Date();

    // check if jwt is valid
    if (now.after(expirationDate))
      return true;

    return false;
  }

  /**
   * Add a custom properties from UserDetails to JWT
   *
   * @param userDetails
   * @param jBuilder
   */
  private void addCustomPropertiesUserDetailsToJwt(UserInfoDto userDetails, JwtBuilder jBuilder) {

    jBuilder.claim(CLAIM_FIRST_NAME, userDetails.getFirstName());
    jBuilder.claim(CLAIM_LAST_NAME, userDetails.getLastName());
    jBuilder.claim(CLAIM_DISPLAY_NAME, userDetails.getDisplayName());
    jBuilder.claim(CLAIM_EMAIL, userDetails.getMail());
    jBuilder.claim(CLAIM_EMPLOYEE_NUMBER, userDetails.getEmployeeNumber());
    jBuilder.claim(CLAIM_GLOBAL_ID, userDetails.getCapgeminiGlobalID());
    jBuilder.claim(CLAIM_EMPLOYEE_GRADE, userDetails.getGrade());
    jBuilder.claim(CLAIM_OFFICE_NAME, userDetails.getPhysicalDeliveryOfficeName());

  }

  /**
   * Add a custom properties from JWT to UserDetails
   *
   * @param claims
   * @param userDetails
   */
  private void addCustomPropertiesJwtToUserDetails(Claims claims, UserInfoDto userDetails) {

    userDetails.setFirstNameValue((String) claims.get(CLAIM_FIRST_NAME));
    userDetails.setLastNameValue((String) claims.get(CLAIM_LAST_NAME));
    userDetails.setDisplayNameValue((String) claims.get(CLAIM_DISPLAY_NAME));
    userDetails.setMailValue((String) claims.get(CLAIM_EMAIL));
    userDetails.setEmployeeNumberValue((String) claims.get(CLAIM_EMPLOYEE_NUMBER));
    userDetails.setCapgeminiGlobalIDValue((String) claims.get(CLAIM_GLOBAL_ID));
    userDetails.setGradeValue((String) claims.get(CLAIM_EMPLOYEE_GRADE));
    userDetails.setPhysicalDeliveryOfficeNameValue((String) claims.get(CLAIM_OFFICE_NAME));

  }

  /**
   * Set the encodedKey from properties
   *
   * @param encodedKey new value of encoded key
   */
  @Value("${jwt.encodedKey}")
  public final void setEncodedKey(String encodedKey) {

    byte[] decodedKey = DatatypeConverter.parseBase64Binary(encodedKey);
    this.secretKey = new SecretKeySpec(decodedKey, SIGNATURE_ALGORITHM.getJcaName());

  }

}