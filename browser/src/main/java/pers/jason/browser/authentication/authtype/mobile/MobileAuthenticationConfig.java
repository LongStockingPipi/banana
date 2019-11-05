package pers.jason.browser.authentication.authtype.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import pers.jason.browser.authentication.authtype.AuthenticationTypeConfig;
import pers.jason.core.property.AuthenticationChannel;
import pers.jason.core.property.BananaProperties;
import pers.jason.core.support.AuthenticationType;

import static pers.jason.core.support.AuthenticationType.mobile;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 10:43
 */
@Component("mobileAuthenticationConfig")
public class MobileAuthenticationConfig extends AuthenticationTypeConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private BananaProperties bananaProperties;

  @Autowired
  private SimpleUrlAuthenticationFailureHandler failureHandler;

  @Autowired
  private SimpleUrlAuthenticationSuccessHandler successHandler;

  @Override
  public AuthenticationType getAuthType() {
    return mobile;
  }

  @Override
  public void configure(HttpSecurity builder) {

    AuthenticationChannel mobileChannel = bananaProperties.getAuth().getTypes().get("mobile");

    final String mobileAuthRequestUrl = mobileChannel.getAuthRequestUrl();

    final String paramName = mobileChannel.getParamsName()[0];

    MobileAuthenticationFilter mobileAuthenticationFilter =
        new MobileAuthenticationFilter(mobileAuthRequestUrl, paramName);

    mobileAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
    mobileAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
    mobileAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);

    MobileAuthenticationProvider authenticationProvider = new MobileAuthenticationProvider(userDetailsService);

    builder.authenticationProvider(authenticationProvider)
        .addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }


}
