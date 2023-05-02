package com.ccsw.codewar.config.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    /**
     * @return UserDetailsJWTDto
     */
    public static UserInfoDto getUserDetails() {

        return (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

}
