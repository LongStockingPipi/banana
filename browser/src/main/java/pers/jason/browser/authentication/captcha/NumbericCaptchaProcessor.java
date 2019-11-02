package pers.jason.browser.authentication.captcha;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import pers.jason.browser.authentication.support.CodeType;
import pers.jason.browser.exception.ValidateCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static pers.jason.browser.exception.ValidateCodeException.EMPTY_IN_REQUEST;
import static pers.jason.browser.exception.ValidateCodeException.EXPIRED;
import static pers.jason.browser.exception.ValidateCodeException.MISMATCH;
import static pers.jason.browser.exception.ValidateCodeException.NULL_IN_CACHE;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/1 18:35
 */
public abstract class NumbericCaptchaProcessor extends AbstractCaptchaProcessor {

  @Override
  public void validate(ServletWebRequest webRequest) {
    String codeInRequest = obtainValidateCode(webRequest.getRequest());

    String cacheKey = VALIDATE_CODE_PREFIX + getCaptchaType().getName();
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
    String cacheKey = VALIDATE_CODE_PREFIX + getCaptchaType().getName();
    webRequest.getRequest().getSession().setAttribute(cacheKey, captcha);
  }

  protected abstract CodeType getCaptchaType();

  protected abstract String obtainValidateCode(HttpServletRequest request);
}
