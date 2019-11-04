package pers.jason.core.property;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/4 17:30
 */
public class AuthProperties {

  private String signInUrl = "/auth/mobile";

  private String loginPage = "/page/auth";

  private String loginRequestType = "ajax";

  public String getSignInUrl() {
    return signInUrl;
  }

  public void setSignInUrl(String signInUrl) {
    this.signInUrl = signInUrl;
  }

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
}
