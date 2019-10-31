package pers.jason.browser.authentication.authtype.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import pers.jason.browser.authentication.DefaultAuthenticationFailedHandler;
import pers.jason.browser.authentication.DefaultAuthenticationSuccessHandler;
import pers.jason.browser.authentication.authtype.AuthenticationTypeConfig;
import pers.jason.core.support.AuthenticationType;

import java.util.HashMap;
import java.util.Map;

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
  private DefaultAuthenticationFailedHandler authenticationFailedHandler;

  @Autowired
  private DefaultAuthenticationSuccessHandler authenticationSuccessHandler;

  @Override
  protected AuthenticationType getAuthType() {
    return mobile;
  }


  @Override
  public void configure(HttpSecurity builder) {
    String processUrlKey = mobile.getName() + "ProcessUrl";
    String paramKey = mobile.getName() + "ParamName";

    Map<String, String> props = new HashMap<>();

    String processUrl = props.get(processUrlKey);
    String paramName = props.get(paramKey);


    MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter(processUrl, paramName);

    mobileAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
    mobileAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    mobileAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailedHandler);

    MobileAuthenticationProvider authenticationProvider = new MobileAuthenticationProvider(userDetailsService);


    builder.authenticationProvider(authenticationProvider)
        .addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }


}
