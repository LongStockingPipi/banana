package pers.jason.browser.authentication.captcha.sms;

import org.apache.commons.lang.RandomStringUtils;
import pers.jason.browser.authentication.captcha.Captcha;
import pers.jason.browser.authentication.captcha.CaptchaGenerator;
import pers.jason.core.property.SecurityProperties;

/**
 * @Author 姜治昊
 * @Description 校验码生成器
 * @Date 2019/10/30 8:54
 */
public class SmsCaptchaGenerator implements CaptchaGenerator {

  private SecurityProperties securityProperties;

  public SmsCaptchaGenerator(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  public Captcha generate() {
    String code = RandomStringUtils.randomNumeric(securityProperties.getSmsCodeLength());
    SmsCaptcha validateCode = new SmsCaptcha(securityProperties.getExpireTime(), code);
    return validateCode;
  }

}
