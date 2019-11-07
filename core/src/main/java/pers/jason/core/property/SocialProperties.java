package pers.jason.core.property;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 16:23
 */
public class SocialProperties {

  private String signUpPage = "/signUp";

  private String signUpRequestUrl = "/user/signup";

  private String bindingRequestUrl = "/user/binding";

  private Map<String, SocialChannel> socials = new HashMap<>();

  public Map<String, SocialChannel> getSocials() {
    return socials;
  }

  public void setSocials(Map<String, SocialChannel> socials) {
    this.socials = socials;
  }

  public String getSignUpPage() {
    return signUpPage;
  }

  public void setSignUpPage(String signUpPage) {
    this.signUpPage = signUpPage;
  }

  public String getSignUpRequestUrl() {
    return signUpRequestUrl;
  }

  public void setSignUpRequestUrl(String signUpRequestUrl) {
    this.signUpRequestUrl = signUpRequestUrl;
  }

  public String getBindingRequestUrl() {
    return bindingRequestUrl;
  }

  public void setBindingRequestUrl(String bindingRequestUrl) {
    this.bindingRequestUrl = bindingRequestUrl;
  }
}
