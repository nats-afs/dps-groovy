package com.asforsoft.dps.security.auth.jwt.filter

import com.asforsoft.dps.service.JWTService
import com.asforsoft.dps.service.JWTServiceImpl
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager
    private JWTService jwtService

     JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"))

        this.jwtService = jwtService
    }

    @Override
    Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String username = obtainUsername(request)
        String password = obtainPassword(request)

        if (username != null && password != null) {
            logger.info("Username desde request parameter (form-data): " + username)
            logger.info("Password desde request parameter (form-data): " + password)

        } else {
            com.asforsoft.dps.model.User user
            try {

                user = new ObjectMapper().readValue(request.getInputStream(), com.asforsoft.dps.model.User.class)

                username = user.getUsername()
                password = user.getPassword()

                logger.info("Username desde request InputStream (raw): " + username)
                logger.info("Password desde request InputStream (raw): " + password)

            } catch (JsonParseException|JsonMappingException|IOException e) {
                e.printStackTrace()
            }
        }

        username = username.trim()

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password)

        authenticationManager.authenticate(authToken)
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = jwtService.create(authResult)

        response.addHeader(JWTServiceImpl.TOKEN_PREFIX, JWTServiceImpl.TOKEN_PREFIX + token)

        Map<String, Object> body = new HashMap<String, Object>()
        body.put("token", token)
        body.put("user", (User) authResult.getPrincipal())
        body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito!", ((User) authResult.getPrincipal()).getUsername()))

        response.getWriter().write(new ObjectMapper().writeValueAsString(body))
        response.setStatus(200)
        response.setContentType("application/json")
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        Map<String, Object> body = new HashMap<String, Object>()
        body.put("mensaje", "Error de autenticación: username o password incorrecto!")
        body.put("error", failed.getMessage())

        response.getWriter().write(new ObjectMapper().writeValueAsString(body))
        response.setStatus(401)
        response.setContentType("application/json")
    }

}
