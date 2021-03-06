package pers.jason.browser.authentication.captcha.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.jason.core.notification.DefaultSmsSender;
import pers.jason.core.notification.SimpleSmsNotification;
import pers.jason.core.property.BananaProperties;

/**
 * @Author 姜治昊
 * @Description  TODO: If you don't need SMS verification code, then these beans should not be injected into the
 *                    spring container, we can use @Conditional to achieve
 * @Date 2019/10/30 16:15
 */
@Configuration
public class SmsCaptchaBeanConfig {

  @Autowired
  private BananaProperties bananaProperties;

  @Bean
  @ConditionalOnMissingBean({SimpleSmsNotification.class})
  public SimpleSmsNotification smsNotification() {
    return new DefaultSmsSender();
  }

  @Bean
  @ConditionalOnMissingBean({SmsCaptchaGenerator.class})
  public SmsCaptchaGenerator smsCaptchaGenerator() {
    return new SmsCaptchaGenerator(bananaProperties);
  }

}
