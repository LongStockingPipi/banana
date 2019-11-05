package pers.jason.core.property;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 9:24
 */
public class MobileAuthProperties {

  private String authRequestUrl = "/auth/mobile";

  private String paramName = "mobile";

  public String getAuthRequestUrl() {
    return authRequestUrl;
  }

  public void setAuthRequestUrl(String authRequestUrl) {
    this.authRequestUrl = authRequestUrl;
  }

  public String getParamName() {
    return paramName;
  }

  public void setParamName(String paramName) {
    this.paramName = paramName;
  }
}
