package pers.jason.browser.authentication.captcha.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import pers.jason.browser.authentication.captcha.Captcha;
import pers.jason.browser.authentication.captcha.CaptchaGenerator;
import pers.jason.browser.authentication.captcha.NumbericCaptchaProcessor;
import pers.jason.browser.authentication.support.CodeType;
import pers.jason.core.notification.SimpleSmsNotification;
import pers.jason.core.property.BananaProperties;

import javax.servlet.http.HttpServletRequest;

import static pers.jason.browser.authentication.support.CodeType.sms;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 10:11
 */
@Component
public class SmsCaptchaProcessor extends NumbericCaptchaProcessor {

  @Autowired
  private SmsCaptchaGenerator captchaGenerator;

  @Autowired
  private BananaProperties bananaProperties;

  @Autowired
  private SimpleSmsNotification smsNotification;

  @Override
  public CaptchaGenerator getCaptchaGenerator() {
    return captchaGenerator;
  }

  @Override
  protected void send(ServletWebRequest webRequest, Captcha captcha) {
    String tel = obtainParamTel(webRequest.getRequest());
    smsNotification.sendSms(tel, captcha.getValue());
  }

  @Override
  protected CodeType getCaptchaType() {
    return sms;
  }

  @Override
  protected String obtainValidateCode(HttpServletRequest request) {
    return request.getParameter(bananaProperties.getCaptcha().getSms().getParamName());
  }

  private String obtainParamTel(HttpServletRequest request) {
    return request.getParameter(bananaProperties.getAuth().getTypes().get("mobile").getParamsName()[0]);
  }

}
