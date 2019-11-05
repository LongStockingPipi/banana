package pers.jason.browser.authentication.captcha.sms;

import org.apache.commons.lang.RandomStringUtils;
import pers.jason.browser.authentication.captcha.Captcha;
import pers.jason.browser.authentication.captcha.CaptchaGenerator;
import pers.jason.core.property.BananaProperties;

/**
 * @Author 姜治昊
 * @Description 校验码生成器
 * @Date 2019/10/30 8:54
 */
public class SmsCaptchaGenerator implements CaptchaGenerator {

  private BananaProperties bananaProperties;

  public SmsCaptchaGenerator(BananaProperties bananaProperties) {
    this.bananaProperties = bananaProperties;
  }

  @Override
  public Captcha generate() {
    String code = RandomStringUtils.randomNumeric(bananaProperties.getCaptcha().getSms().getLength());
    SmsCaptcha validateCode = new SmsCaptcha(bananaProperties.getCaptcha().getSms().getExpireIn(), code);
    return validateCode;
  }

}
