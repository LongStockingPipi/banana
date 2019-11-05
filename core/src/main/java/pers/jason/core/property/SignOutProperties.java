package pers.jason.core.property;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 10:23
 */
public class SignOutProperties {

  private String signOutUrl = "/logout";

  private String requestType = "form";

  public String getSignOutUrl() {
    return signOutUrl;
  }

  public void setSignOutUrl(String signOutUrl) {
    this.signOutUrl = signOutUrl;
  }

  public String getRequestType() {
    return requestType;
  }

  public void setRequestType(String requestType) {
    this.requestType = requestType;
  }
}
