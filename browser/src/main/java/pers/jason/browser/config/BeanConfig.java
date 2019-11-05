package pers.jason.browser.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import pers.jason.browser.authentication.DefaultAuthenticationFailedHandler;
import pers.jason.browser.authentication.DefaultAuthenticationSuccessHandler;
import pers.jason.browser.authentication.DefaultSuccessSignOutHandler;
import pers.jason.browser.authentication.captcha.sms.SmsCaptchaGenerator;
import pers.jason.core.property.BananaProperties;
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
  private BananaProperties bananaProperties;

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
  @ConditionalOnMissingBean({AuthenticationSuccessHandler.class})
  public SimpleUrlAuthenticationSuccessHandler defaultAuthenticationSuccessHandler() {
    SimpleUrlAuthenticationSuccessHandler handler =
        new DefaultAuthenticationSuccessHandler(objectMapper, bananaProperties);
    return handler;
  }

  @Bean
  @ConditionalOnMissingBean({AuthenticationFailureHandler.class})
  public SimpleUrlAuthenticationFailureHandler defaultAuthenticationFailedHandler() {
    SimpleUrlAuthenticationFailureHandler handler =
        new DefaultAuthenticationFailedHandler(objectMapper, bananaProperties);
    return handler;
  }

  @Bean
  @ConditionalOnMissingBean({SmsCaptchaGenerator.class})
  public SmsCaptchaGenerator smsCaptchaGenerator() {
    SmsCaptchaGenerator smsCaptchaGenerator =
        new SmsCaptchaGenerator(bananaProperties);
    return smsCaptchaGenerator;
  }

  @Bean
  @ConditionalOnMissingBean({LogoutSuccessHandler.class})
  public DefaultSuccessSignOutHandler signOutSuccessHandler() {
    DefaultSuccessSignOutHandler successSignOutHandler =
        new DefaultSuccessSignOutHandler(bananaProperties, objectMapper);
    return successSignOutHandler;
  }

}
