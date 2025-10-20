package com.personal.todolistapi.common;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;

public class GetUserUUID {
    public static String getUserUUID(Jwt jwt) {
        String UUID = JwtClaimNames.SUB;
        return jwt.getClaimAsString(UUID);
    }
}
