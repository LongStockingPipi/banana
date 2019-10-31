package pers.jason.browser.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import pers.jason.browser.authentication.captcha.AbstractCaptchaProcessor;
import pers.jason.browser.authentication.support.CodeType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 14:07
 */
@RestController
public class BrowserController {

  private static final String PROCESSOR_SUFFIX = "CaptchaProcessor";

  @Autowired
  private Map<String, AbstractCaptchaProcessor> processorMap;

  @GetMapping("captcha/{type}")
  public void code(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response) {
    if(StringUtils.isEmpty(type)) {
      throw new RuntimeException("param type can not be null!");
    }
    type = type.toLowerCase().trim();
    CodeType codeType = CodeType.valueOf(type);
    String processorName = codeType.getName() + PROCESSOR_SUFFIX;
    AbstractCaptchaProcessor processor = processorMap.get(processorName);
    processor.requestCaptcha(new ServletWebRequest(request, response));
  }

  @GetMapping("captcha/validate/{type}/{code}")
  public String validate(@PathVariable("type") String type, @PathVariable("code") String code
      , HttpServletRequest request, HttpServletResponse response) {

    if(StringUtils.isEmpty(type)) {
      throw new RuntimeException("param type can not be null!");
    }
    type = type.toLowerCase().trim();
    CodeType codeType = CodeType.valueOf(type);
    String processorName = codeType.getName() + PROCESSOR_SUFFIX;
    AbstractCaptchaProcessor processor = processorMap.get(processorName);

    ServletWebRequest webRequest  = new ServletWebRequest(request, response);
    processor.validate(webRequest);
    return "success";
  }

}
