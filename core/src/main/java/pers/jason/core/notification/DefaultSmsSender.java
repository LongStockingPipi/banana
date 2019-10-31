package pers.jason.core.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 15:31
 */
public class DefaultSmsSender implements SimpleSmsNotification {

  private static final Logger logger = LoggerFactory.getLogger(DefaultSmsSender.class);

  @Override
  public void sendSms(String tel, String message) {
    if(StringUtils.isEmpty(tel)) {
      throw new RuntimeException("tel number can not be null!");
    }
    if(StringUtils.isEmpty(message)) {
      throw new RuntimeException("message can not be null!");
    }
    logger.info("notificate " + tel + ": " + message);
  }
}
