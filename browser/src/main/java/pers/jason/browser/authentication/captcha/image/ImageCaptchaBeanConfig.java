package pers.jason.browser.authentication.captcha.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.jason.core.property.SecurityProperties;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/1 18:25
 */
@Configuration
public class ImageCaptchaBeanConfig {

  @Autowired
  private SecurityProperties securityProperties;

  @Bean
  @ConditionalOnMissingBean({ImageCaptchaGenerator.class})
  public ImageCaptchaGenerator imageCaptchaGenerator() {
    return new ImageCaptchaGenerator(securityProperties);
  }

}
