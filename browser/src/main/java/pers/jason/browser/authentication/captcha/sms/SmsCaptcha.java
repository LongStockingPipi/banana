package pers.jason.browser.authentication.captcha.sms;

import lombok.Data;
import pers.jason.browser.authentication.captcha.Captcha;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 9:04
 */
public class SmsCaptcha extends Captcha {

  private String code;

  public SmsCaptcha(long expireTime, String code) {
    super(expireTime);
    this.code = code;
  }

  @Override
  public String getValue() {
    return code;
  }
}
