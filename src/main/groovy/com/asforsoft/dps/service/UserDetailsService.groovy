package com.asforsoft.dps.service


import com.asforsoft.dps.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("UserDetailsService")
class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  @Autowired
  private UserRepository userRepository

  private Logger logger = LoggerFactory.getLogger(UserDetailsService.class)

  @Override
  @Transactional(readOnly = true)
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    com.asforsoft.dps.model.User user = userRepository.findByUsername(username)

    if (user == null) {
      logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!")
      throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!")
    }

    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>()

    for (role in user.getRoles()) {
      logger.info("ROLE: ".concat(role.getAuthority()))
      authorities.add(new SimpleGrantedAuthority(role.getAuthority()))
    }

    if (authorities.isEmpty()) {
      logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!")
      throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!")
    }

    new User(
        user.getUsername(),
        user.getPassword(),
        user.getEnabled(),
        true,
        true,
        true,
        authorities
    )

  }

}
