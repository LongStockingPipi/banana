package pers.jason.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import pers.jason.browser.authentication.authtype.AuthenticationTypeConfig;
import pers.jason.browser.authentication.captcha.CaptchaFilterConfig;
import pers.jason.core.property.BananaProperties;

import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:05
 */
@Configuration
public class BananaSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

  private final String serviceNameSuffix = "AuthenticationConfig";

  @Autowired
  private CaptchaFilterConfig captchaFilterConfig;

  @Autowired
  private SimpleUrlAuthenticationFailureHandler defaultAuthenticationFailedHandler;

  @Autowired
  private SimpleUrlAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

  @Autowired
  private Map<String, AuthenticationTypeConfig> authenticationTypeConfigMap;

  @Autowired
  private BananaProperties bananaProperties;

  private static final Logger logger = LoggerFactory.getLogger(BananaSecurityConfigurerAdapter.class);

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    logger.debug("configuring custom authentication");
    for(String authType : authenticationTypeConfigMap.keySet()) {
      AuthenticationTypeConfig authenticationTypeConfig = authenticationTypeConfigMap.get(authType);
      http.apply(authenticationTypeConfig);
    }

    String[] uris = getPermitUri();

    http
        //general configuration
      .apply(captchaFilterConfig)
      .and()

      //username and password authentication configuration
      .formLogin()
      .loginPage(bananaProperties.getAuth().getLoginPage())
      .loginProcessingUrl(bananaProperties.getAuth().getSignInUrl())
      .permitAll()
      .successHandler(defaultAuthenticationSuccessHandler)
      .failureHandler(defaultAuthenticationFailedHandler)

      //universal configuration
      .and()
      .authorizeRequests()
      .antMatchers(uris)
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .csrf().disable()
      ;
  }

  protected String[] getPermitUri() {
    String[] uris = new String[7];

    //signIn loginPage system request
    uris[0] = bananaProperties.getAuth().getLoginPage();
    uris[1] = bananaProperties.getAuth().getSignInUrl();

    //js css static resource
    uris[2] = "/**/*.js";
    uris[3] = "/**/*.css";
    uris[4] = "/**/*.jpg";

    //captcha
    uris[5] = "/captcha/sms";
    uris[6] = "/captcha/image";
    return uris;
  }

}
