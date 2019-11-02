package pers.jason.browser.authentication.captcha.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import pers.jason.browser.authentication.captcha.Captcha;
import pers.jason.browser.authentication.captcha.CaptchaGenerator;
import pers.jason.browser.authentication.captcha.NumbericCaptchaProcessor;
import pers.jason.browser.authentication.support.CodeType;
import pers.jason.core.property.SecurityProperties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static pers.jason.browser.authentication.support.CodeType.image;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/1 18:32
 */
@Component
public class ImageCaptchaProcessor extends NumbericCaptchaProcessor {

  @Autowired
  private ImageCaptchaGenerator imageCaptchaGenerator;

  @Autowired
  private SecurityProperties securityProperties;

  @Override
  protected CodeType getCaptchaType() {
    return image;
  }

  @Override
  protected String obtainValidateCode(HttpServletRequest request) {
    return request.getParameter(securityProperties.getValidateCodeParamName());
  }

  @Override
  protected CaptchaGenerator getCaptchaGenerator() {
    return imageCaptchaGenerator;
  }

  @Override
  protected void send(ServletWebRequest webRequest, Captcha captcha) throws IOException {
    ImageCaptcha imageCaptcha = (ImageCaptcha) captcha;
    ImageIO.write(imageCaptcha.getImage(), "JPEG", webRequest.getResponse().getOutputStream());
  }
}
