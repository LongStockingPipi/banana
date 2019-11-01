package pers.jason.browser.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import pers.jason.browser.authentication.DefaultAuthenticationFailedHandler;
import pers.jason.browser.authentication.DefaultAuthenticationSuccessHandler;
import pers.jason.browser.authentication.captcha.sms.SmsCaptchaGenerator;
import pers.jason.core.property.SecurityProperties;
import pers.jason.core.support.Properties;

import java.io.IOException;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/29 8:25
 */
@Configuration
public class BeanConfig {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private SecurityProperties securityProperties;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public Properties globalProperties() throws IOException {
    final String conf = "banana-security.properties";
    String conPath = System.getProperty("user.dir") + "\\example\\src\\main\\resources\\" + conf;
    Properties properties = new Properties(null, conPath);
    return properties;
  }

  @Bean
  @ConditionalOnMissingBean({SimpleUrlAuthenticationSuccessHandler.class})
  public SimpleUrlAuthenticationSuccessHandler defaultAuthenticationSuccessHandler() {
    SimpleUrlAuthenticationSuccessHandler handler =
        new DefaultAuthenticationSuccessHandler(objectMapper, securityProperties);
    return handler;
  }

  @Bean
  @ConditionalOnMissingBean({SimpleUrlAuthenticationFailureHandler.class})
  public SimpleUrlAuthenticationFailureHandler defaultAuthenticationFailedHandler() {
    SimpleUrlAuthenticationFailureHandler handler =
        new DefaultAuthenticationFailedHandler(objectMapper, securityProperties);
    return handler;
  }

  @Bean
  @ConditionalOnMissingBean({SmsCaptchaGenerator.class})
  public SmsCaptchaGenerator smsCaptchaGenerator() {
    SmsCaptchaGenerator smsCaptchaGenerator =
        new SmsCaptchaGenerator(securityProperties);
    return smsCaptchaGenerator;
  }

}
