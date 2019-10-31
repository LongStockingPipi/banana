package pers.jason.browser.authentication.captcha.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import pers.jason.browser.authentication.captcha.Captcha;
import pers.jason.browser.authentication.captcha.AbstractCaptchaProcessor;
import pers.jason.browser.authentication.captcha.CaptchaGenerator;
import pers.jason.browser.authentication.support.CodeType;
import pers.jason.browser.exception.ValidateCodeException;
import pers.jason.core.notification.SimpleSmsNotification;
import pers.jason.core.property.SecurityProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static pers.jason.browser.authentication.support.CodeType.sms;
import static pers.jason.browser.exception.ValidateCodeException.EMPTY_IN_REQUEST;
import static pers.jason.browser.exception.ValidateCodeException.EXPIRED;
import static pers.jason.browser.exception.ValidateCodeException.MISMATCH;
import static pers.jason.browser.exception.ValidateCodeException.NULL_IN_CACHE;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 10:11
 */
@Component
public class SmsCaptchaProcessor extends AbstractCaptchaProcessor {

  @Autowired
  private SmsCaptchaGenerator captchaGenerator;

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private SimpleSmsNotification smsNotification;

  @Override
  public CaptchaGenerator getCaptchaGenerator() {
    return captchaGenerator;
  }

  @Override
  public void validate(ServletWebRequest webRequest) {

    String codeInRequest = obtainValidateCode(webRequest.getRequest());

    String cacheKey = VALIDATE_CODE_PREFIX + sms.getName();
    HttpSession session = webRequest.getRequest().getSession();
    Captcha codeInCache = (Captcha) session.getAttribute(cacheKey);

    if(StringUtils.isEmpty(codeInRequest)) {
      throw new ValidateCodeException("the captcha can not be null!", EMPTY_IN_REQUEST);
    }

    if(null == codeInCache || StringUtils.isEmpty(codeInCache.getValue())) {
      throw new ValidateCodeException("the captcha does not exist!", NULL_IN_CACHE);
    }

    if(codeInCache.wasExpried()) {
      throw new ValidateCodeException("the captcha has expired!", EXPIRED);
    }

    String code = codeInCache.getValue();
    if(!codeInRequest.equals(code)) {
      throw new ValidateCodeException("the captcha does not match!", MISMATCH);
    }

  }

  @Override
  protected void save(ServletWebRequest webRequest, Captcha captcha) {
    String cacheKey = VALIDATE_CODE_PREFIX + sms.getName();
    webRequest.getRequest().getSession().setAttribute(cacheKey, captcha);
  }

  @Override
  protected void send(ServletWebRequest webRequest, Captcha captcha) {
    String tel = obtainParamTel(webRequest.getRequest());
    smsNotification.sendSms(tel, captcha.getValue());
  }

  private String obtainValidateCode(HttpServletRequest request) {
    return request.getParameter(securityProperties.getValidateCodeParamName());
  }

  private String obtainParamTel(HttpServletRequest request) {
    return request.getParameter(securityProperties.getMobileNumParamName());
  }

}
