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
}
