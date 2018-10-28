package com.asforsoft.dps.service

import io.jsonwebtoken.Claims
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

interface JWTService {

     String create(Authentication auth) throws IOException;

     boolean validate(String token);

     Claims getClaims(String token);

     String getUsername(String token);

     Collection<? extends GrantedAuthority> getRoles(String token) throws IOException;

     String resolve(String token);
}
