package pers.jason.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import pers.jason.browser.authentication.AuthFilterConfig;
import pers.jason.browser.authentication.authtype.AuthenticationTypeConfig;
import pers.jason.core.property.SecurityProperties;

import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:05
 */
@Configuration
public class BananaSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthFilterConfig authFilterConfig;

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private SimpleUrlAuthenticationFailureHandler defaultAuthenticationFailedHandler;

  @Autowired
  private SimpleUrlAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

  @Autowired
  private Map<String, AuthenticationTypeConfig> authenticationTypeConfigMap;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    String configName = securityProperties.getAuthType();
    AuthenticationTypeConfig authenticationTypeConfig = authenticationTypeConfigMap.get(configName);
    http
        .apply(authFilterConfig)
        .and()
        .apply(authenticationTypeConfig)
        .and()
        //username and password authentication configuration
        .formLogin()
        .loginPage(securityProperties.getLoginPage())
        .loginProcessingUrl(securityProperties.getAuthRequestUri())
        .permitAll()
        .successHandler(defaultAuthenticationSuccessHandler)
        .failureHandler(defaultAuthenticationFailedHandler)

        //universal configuration
        .and()
        .authorizeRequests()
        .antMatchers(securityProperties.getLoginPage(), securityProperties.getAuthRequestUri()
            , "/captcha/*", "/captcha/validate/*/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable()
        ;
  }

}
