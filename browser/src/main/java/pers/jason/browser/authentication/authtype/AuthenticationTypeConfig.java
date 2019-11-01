package pers.jason.browser.authentication.authtype;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import pers.jason.core.support.AuthenticationType;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 10:44
 */
public abstract class AuthenticationTypeConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private String processUrl;

  private String paramName;

  public AuthenticationTypeConfig() {
  }

  public String getProcessUrl() {
    return processUrl;
  }

  public String getParamName() {
    return paramName;
  }

  public void setProcessUrl(String processUrl) {
    this.processUrl = processUrl;
  }

  public void setParamName(String paramName) {
    this.paramName = paramName;
  }

  public abstract AuthenticationType getAuthType();
}
