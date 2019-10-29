package pers.jason.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pers.jason.example.entity.User;
import pers.jason.example.rest.service.UserService;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/29 10:08
 */
@Service
public class MyUserDetailService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = userService.findUserByUsername(s);
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
        user.getUsername(), passwordEncoder.encode(user.getPassword()), enabled, accountNonExpired
    , credentialsNonExpired, accountNonLocked, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
    );
    return userDetails;
  }

}
