package pers.jason.browser.authentication.captcha;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 9:27
 */
public abstract class AbstractCaptchaProcessor {

  protected static final String VALIDATE_CODE_PREFIX = "validateCode_";

  /**
   * 获取校验码生成器
   * @return
   */
  protected abstract CaptchaGenerator getCaptchaGenerator();

  /**
   * 校验验证码
   * @param webRequest
   */
  public abstract void validate(ServletWebRequest webRequest);

  /**
   * 缓存验证码
   * @param webRequest
   * @param captcha
   */
  protected abstract void save(ServletWebRequest webRequest, Captcha captcha);

  /**
   * 发送验证码
   * @param webRequest
   * @param captcha
   */
  protected abstract void send(ServletWebRequest webRequest, Captcha captcha);

  public final void requestCaptcha(ServletWebRequest webRequest) {
    Captcha captcha = getCaptchaGenerator().generate();
    save(webRequest, captcha);
    send(webRequest, captcha);
  }

}
