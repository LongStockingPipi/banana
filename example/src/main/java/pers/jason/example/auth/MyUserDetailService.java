package pers.jason.example.auth;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import pers.jason.example.entity.User;
import pers.jason.example.rest.service.UserService;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/29 10:08
 */
@Service
public class MyUserDetailService implements UserDetailsService, SocialUserDetailsService {

  private final String NULL_PASSWD = "null";

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    User user = userService.findByUsernameOrPhoneNumber(s);
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    String username = user.getUsername();
    String password = user.getPassword();
    final String _username = Strings.isNullOrEmpty(username) ? user.getTel() : username;
    final String _password = Strings.isNullOrEmpty(password) ? NULL_PASSWD : password;

    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
        _username, passwordEncoder.encode(_password), enabled, accountNonExpired
    , credentialsNonExpired, accountNonLocked, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
    );
    return userDetails;
  }

  @Override
  public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
    String password = getPassword(userId);
    return new SocialUser(userId, password, true, true, true
        , true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }

  private String getPassword(String s) {
    return "p_" + s + "_";
  }

}
