package pers.jason.browser.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 17:06
 */
@Component("authFilterConfig")
public class AuthFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Autowired
  private ValidateCodeFilter validateCodeFilter;

  @Override
  public void configure(HttpSecurity security) {
    security.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
