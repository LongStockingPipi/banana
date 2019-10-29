package pers.jason.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import pers.jason.core.property.SecurityProperties;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:05
 */
@Configuration
public class BananaSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private SimpleUrlAuthenticationFailureHandler defaultAuthenticationFailedHandler;

  @Autowired
  private SimpleUrlAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        //配置认证处理
//        .authenticationProvider(authenticationProvider())
        //登录配置
        .formLogin()
        .loginPage(securityProperties.getLoginPage())
        .loginProcessingUrl(securityProperties.getAuthRequestUri())
        .permitAll()
        .successHandler(defaultAuthenticationSuccessHandler)
        .failureHandler(defaultAuthenticationFailedHandler)
        //通用配置
        .and()
        .authorizeRequests()
        .antMatchers(securityProperties.getLoginPage(), securityProperties.getAuthRequestUri())
        .permitAll()
        .anyRequest() // 任何请求
        .authenticated()
        .and()
        .csrf().disable()
        ;
  }

}
