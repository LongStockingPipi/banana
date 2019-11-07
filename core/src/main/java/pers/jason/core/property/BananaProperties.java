package pers.jason.core.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/4 16:19
 */
@ConfigurationProperties(prefix = "banana.security")
public class BananaProperties {

  private CaptchaProperties captcha;

  private AuthProperties auth;

  private String welcomePage;

  private SignOutProperties signOut;

  private SocialProperties social;

  public CaptchaProperties getCaptcha() {
    return captcha;
  }

  public void setCaptcha(CaptchaProperties captcha) {
    this.captcha = captcha;
  }

  public AuthProperties getAuth() {
    return auth;
  }

  public void setAuth(AuthProperties auth) {
    this.auth = auth;
  }

  public String getWelcomePage() {
    return welcomePage;
  }

  public void setWelcomePage(String welcomePage) {
    this.welcomePage = welcomePage;
  }

  public SignOutProperties getSignOut() {
    return signOut;
  }

  public void setSignOut(SignOutProperties signOut) {
    this.signOut = signOut;
  }

  public SocialProperties getSocial() {
    return social;
  }

  public void setSocial(SocialProperties social) {
    this.social = social;
  }
}
