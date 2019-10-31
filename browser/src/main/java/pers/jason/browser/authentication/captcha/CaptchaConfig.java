package pers.jason.browser.authentication.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.jason.browser.authentication.captcha.sms.SmsCaptchaGenerator;
import pers.jason.core.notification.DefaultSmsSender;
import pers.jason.core.notification.SimpleSmsNotification;
import pers.jason.core.property.SecurityProperties;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 16:15
 */
@Configuration
public class CaptchaConfig {

  @Autowired
  private SecurityProperties securityProperties;

  @Bean
  @ConditionalOnMissingBean({SimpleSmsNotification.class})
  public SimpleSmsNotification smsNotification() {
    return new DefaultSmsSender();
  }

  @Bean
  @ConditionalOnMissingBean({SmsCaptchaGenerator.class})
  public SmsCaptchaGenerator smsCaptchaGenerator() {
    return new SmsCaptchaGenerator(securityProperties);
  }

}
