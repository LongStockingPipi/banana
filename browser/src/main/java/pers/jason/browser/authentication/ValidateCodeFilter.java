package pers.jason.browser.authentication;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import pers.jason.browser.authentication.captcha.sms.SmsCaptchaProcessor;
import pers.jason.core.property.SecurityProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pers.jason.core.support.HttpRequestMethod.POST;

/**
 * @Author 姜治昊
 * @Description It does not belong to the authentication filter itself, only the checksum is correct.
 * @Date 2019/10/30 9:10
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

  private final AntPathMatcher pathMatcher = new AntPathMatcher();

  @Autowired
  private SmsCaptchaProcessor smsValidateCodeProcessor;

  @Autowired
  private SecurityProperties securityProperties;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
      , FilterChain filterChain) throws ServletException, IOException {

    if(pathMatcher.match(securityProperties.getAuthRequestUri(), httpServletRequest.getRequestURI())) {

      smsValidateCodeProcessor.validate(
          new ServletWebRequest(httpServletRequest, httpServletResponse));
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
