package pers.jason.browser.authentication.captcha;

/**
 * @author 姜治昊
 */
public interface CaptchaGenerator {

  /**
   * 生成校验码
   * @return
   */
  Captcha generate();

}
