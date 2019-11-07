package pers.jason.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;
import pers.jason.browser.authentication.authtype.AuthenticationTypeConfig;
import pers.jason.browser.authentication.captcha.CaptchaFilterConfig;
import pers.jason.core.property.AuthenticationChannel;
import pers.jason.core.property.BananaProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
  private SpringSocialConfigurer springSocialConfigurer;

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

    List<String> uris = getPermitUri();
    String defaultSignInRequestUri = bananaProperties.getAuth().getTypes().get("default").getAuthRequestUrl();

    http
      //general configuration
      .apply(captchaFilterConfig)
      .and()

      //spring social
      .apply(springSocialConfigurer)
      .and()

      //username and password authentication configuration
      .formLogin()
      .loginPage(bananaProperties.getAuth().getLoginPage())
      .loginProcessingUrl(defaultSignInRequestUri)
      .permitAll()
      .successHandler(defaultAuthenticationSuccessHandler)
      .failureHandler(defaultAuthenticationFailedHandler)

      //sign out
      .and()
      .logout()
      .logoutUrl("/signout")

      //universal configuration
      .and()
      .authorizeRequests()
      .antMatchers(uris.toArray(new String[uris.size()]))
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .csrf().disable()
      ;
  }

  protected List<String> getPermitUri() {

    List<String> uris = new ArrayList<>();

    Map<String, AuthenticationChannel> channelMap = bananaProperties.getAuth().getTypes();


    //login page
    uris.add(bananaProperties.getAuth().getLoginPage());

    //sign up page
    uris.add(bananaProperties.getSocial().getSignUpPage());

    uris.add(bananaProperties.getSocial().getSignUpRequestUrl());
    uris.add(bananaProperties.getSocial().getBindingRequestUrl());

    //captcha code request
    uris.addAll(Arrays.asList("/captcha/sms", "/captcha/image"));

    //static resources
    uris.addAll(Arrays.asList("/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.gif", "/**/*.png"));

    //authentication uri
    for(String key : channelMap.keySet()) {
      AuthenticationChannel channel = channelMap.get(key);
      if(null != channel) {
        uris.add(channel.getAuthRequestUrl());
      }
    }

    return uris;
  }


}
