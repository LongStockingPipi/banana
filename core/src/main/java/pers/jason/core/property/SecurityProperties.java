package pers.jason.core.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:17
 */
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

  /**
   * ajax请求的uri
   */
  private String requestUriWithAjax = "/";

  /**
   * 请求登录页面
   */
  private String loginPage = "/";

  /**
   * 首页
   */
  private String welcomePage = "/";

  /**
   * 登录请求
   */
  private String authRequestUri = "/";

  public String getRequestUriWithAjax() {
    return requestUriWithAjax;
  }

  public void setRequestUriWithAjax(String requestUriWithAjax) {
    this.requestUriWithAjax = requestUriWithAjax;
  }

  public String getLoginPage() {
    return loginPage;
  }

  public void setLoginPage(String loginPage) {
    this.loginPage = loginPage;
  }

  public String getWelcomePage() {
    return welcomePage;
  }

  public void setWelcomePage(String welcomePage) {
    this.welcomePage = welcomePage;
  }

  public String getAuthRequestUri() {
    return authRequestUri;
  }

  public void setAuthRequestUri(String authRequestUri) {
    this.authRequestUri = authRequestUri;
  }
}
