package pers.jason.browser.authentication.captcha;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import pers.jason.core.property.BananaProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pers.jason.browser.authentication.support.CodeType.image;
import static pers.jason.browser.authentication.support.CodeType.sms;

/**
 * @Author 姜治昊
 * @Description It does not belong to the authentication filter itself, only the checksum is correct.
 * @Date 2019/10/30 9:10
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter implements InitializingBean {

  private static final Logger logger = LoggerFactory.getLogger(CaptchaFilter.class);

  private final AntPathMatcher pathMatcher = new AntPathMatcher();

  @Autowired
  private Map<String, AbstractCaptchaProcessor> captchaProcessorMap;

  @Autowired
  private BananaProperties bananaProperties;

  private final String processorNameSuffix = "CaptchaProcessor";

  private Map<String, List<String>> uri = new HashMap<>();

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
      , FilterChain filterChain) throws ServletException, IOException {

    String requestUri = httpServletRequest.getRequestURI();
    logger.debug("request uri:" + httpServletRequest.getRequestURI());

    String useCaptchaFilter = null;
    for(String type : uri.keySet()) {
      List<String> typeProcessUri = uri.get(type);
      if(!CollectionUtils.isEmpty(typeProcessUri)) {
        for(String uri : typeProcessUri) {
          if(pathMatcher.match(uri, requestUri)) {
            useCaptchaFilter = type;
            break;
          }
        }
      }
    }

    if(!StringUtils.isEmpty(useCaptchaFilter)) {
      String processorName = useCaptchaFilter + processorNameSuffix;
      AbstractCaptchaProcessor processor = captchaProcessorMap.get(processorName);

      if(null == processor) {
        throw new RuntimeException("no valid processor found named: " + processorName);
      }

      logger.debug("registered captcha processor: " + processorName);
      processor.validate(new ServletWebRequest(httpServletRequest, httpServletResponse));
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  @Override
  public void afterPropertiesSet() throws ServletException {
    super.afterPropertiesSet();
    List<String> smsUri = bananaProperties.getCaptcha().getSms().getUri();
    uri.put(sms.getName(), smsUri);

    List<String> imageUri = bananaProperties.getCaptcha().getImage().getUri();
    uri.put(image.getName(), imageUri);
  }
}
