package pers.jason.browser.authentication.captcha;

import java.time.LocalDateTime;

/**
 * @Author 姜治昊
 * @Description Captcha interface
 * @Date 2019/10/30 9:02
 */
public abstract class Captcha {

  private final LocalDateTime localDateTime;

  public abstract String getValue();

  /**
   * @param expireTime expiration time of the check code. The unit of the parameter is seconds.
   */
  public Captcha(long expireTime) {
    super();
    this.localDateTime = LocalDateTime.now().plusSeconds(expireTime);
  }

  public boolean wasExpried() {
    return LocalDateTime.now().isAfter(localDateTime);
  }

}
