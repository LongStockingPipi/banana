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
import pers.jason.core.support.AuthenticationType;
import pers.jason.core.support.Properties;

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
  private Properties properties;

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

    final String processUrlKey = mobile.getName() + "ProcessUrl";
    final String paramKey = mobile.getName() + "ParamName";

    final String processUrl = properties.get(processUrlKey);
    final String paramName = properties.get(paramKey);


    MobileAuthenticationFilter mobileAuthenticationFilter =
        new MobileAuthenticationFilter(processUrl, paramName);

    mobileAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
    mobileAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
    mobileAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);

    MobileAuthenticationProvider authenticationProvider = new MobileAuthenticationProvider(userDetailsService);

    builder.authenticationProvider(authenticationProvider)
        .addFilterBefore(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }


}
