package pers.jason.browser.authentication.authtype;

import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 9:17
 */
public abstract class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {


  protected AuthenticationFilter(String processUrl) {
    super(processUrl);
  }

  protected String obtainParam(HttpServletRequest request, String param) {
    return request.getParameter(param);
  }

}
