package pers.jason.browser.authentication.captcha;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import pers.jason.core.property.SecurityProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
  private SecurityProperties securityProperties;

  private final String processorNameSuffix = "CaptchaProcessor";

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
      , FilterChain filterChain) throws ServletException, IOException {

    if(pathMatcher.match(securityProperties.getAuthRequestUri(), httpServletRequest.getRequestURI())) {

      String captchaType = securityProperties.getCaptchaType();
      if(securityProperties.getNeedCaptcha() && StringUtils.isEmpty(captchaType)) {
        logger.error("no valid processor can register");
        throw new RuntimeException("no valid processor can register");
      }

      String processorName = captchaType + processorNameSuffix;
      AbstractCaptchaProcessor processor = captchaProcessorMap.get(processorName);
      if(null == processor) {
        throw new RuntimeException("no valid processor found named: " + processorName);
      }
      logger.debug("registered captcha processor: " + processorName);
      processor.validate(
          new ServletWebRequest(httpServletRequest, httpServletResponse));

    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
