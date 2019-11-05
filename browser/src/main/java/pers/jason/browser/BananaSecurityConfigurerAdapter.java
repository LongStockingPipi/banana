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
import pers.jason.core.property.AuthenticationChannel;
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
    String defaultSignInRequestUri = bananaProperties.getAuth().getTypes().get("default").getAuthRequestUrl();

    http
        //general configuration
      .apply(captchaFilterConfig)
      .and()

      //username and password authentication configuration
      .formLogin()
      .loginPage(bananaProperties.getAuth().getLoginPage())
      .loginProcessingUrl(defaultSignInRequestUri)
      .permitAll()
      .successHandler(defaultAuthenticationSuccessHandler)
      .failureHandler(defaultAuthenticationFailedHandler)

      .and()
      .logout()
        .logoutUrl("/signout")

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

    Map<String, AuthenticationChannel> channelMap = bananaProperties.getAuth().getTypes();
    int len = channelMap.size();

    String[] uris = new String[len + 6];

    //signIn loginPage system request
    uris[0] = bananaProperties.getAuth().getLoginPage();
    //js css static resource
    uris[1] = "/**/*.js";
    uris[2] = "/**/*.css";
    uris[3] = "/**/*.jpg";

    //captcha
    uris[4] = "/captcha/sms";
    uris[5] = "/captcha/image";

    int i=6;
    for(String key : channelMap.keySet()) {
      AuthenticationChannel channel = channelMap.get(key);
      if(null != channel) {
        uris[i] = channel.getAuthRequestUrl();
      }
      i++;
    }

    printLog(uris);
    return uris;
  }

  private void printLog(String[] uris) {
    for(String uri : uris) {
      logger.info(uri);
    }
  }

}
