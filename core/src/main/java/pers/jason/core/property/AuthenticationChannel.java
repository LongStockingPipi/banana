package pers.jason.core.property;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 9:42
 */
public class AuthenticationChannel {

  private String authRequestUrl = "/auth";

  private String[] paramsName;

  public String getAuthRequestUrl() {
    return authRequestUrl;
  }

  public void setAuthRequestUrl(String authRequestUrl) {
    this.authRequestUrl = authRequestUrl;
  }

  public String[] getParamsName() {
    return paramsName;
  }

  public void setParamsName(String[] paramsName) {
    this.paramsName = paramsName;
  }
}
