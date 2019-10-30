package pers.jason.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import pers.jason.core.property.SecurityProperties;

import javax.sql.DataSource;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:05
 */
@Configuration
public class BananaSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private SimpleUrlAuthenticationFailureHandler defaultAuthenticationFailedHandler;

  @Autowired
  private SimpleUrlAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
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
        .antMatchers(securityProperties.getLoginPage(), securityProperties.getAuthRequestUri())
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable()
        ;
  }

}
