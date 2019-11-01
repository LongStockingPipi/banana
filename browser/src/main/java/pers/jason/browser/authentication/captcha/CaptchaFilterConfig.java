package pers.jason.browser.authentication.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import pers.jason.browser.authentication.captcha.CaptchaFilter;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 17:06
 */
@Component("captchaFilterConfig")
public class CaptchaFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  /**
   * TODO: In the end, we need some checksum filters for the columns.
   *      Users can plug in the configuration and choose which one to use by configuration.
   */
  @Autowired
  private CaptchaFilter validateCodeFilter;

  @Override
  public void configure(HttpSecurity security) {
    security.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
