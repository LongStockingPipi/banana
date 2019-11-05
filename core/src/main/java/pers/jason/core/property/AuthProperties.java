package pers.jason.core.property;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/4 17:30
 */
public class AuthProperties {

  private String loginPage = "/page/auth";

  private String loginRequestType = "ajax";

  private Map<String, AuthenticationChannel> types = new HashMap<>();

  public String getLoginPage() {
    return loginPage;
  }

  public void setLoginPage(String loginPage) {
    this.loginPage = loginPage;
  }

  public String getLoginRequestType() {
    return loginRequestType;
  }

  public void setLoginRequestType(String loginRequestType) {
    this.loginRequestType = loginRequestType;
  }

  public Map<String, AuthenticationChannel> getTypes() {
    return types;
  }

  public void setTypes(Map<String, AuthenticationChannel> types) {
    this.types = types;
  }
}
