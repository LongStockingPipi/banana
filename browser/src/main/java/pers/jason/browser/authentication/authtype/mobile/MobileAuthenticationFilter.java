package pers.jason.browser.authentication.authtype.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import pers.jason.browser.authentication.authtype.AuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pers.jason.core.support.HttpRequestMethod.POST;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 9:26
 */
public class MobileAuthenticationFilter extends AuthenticationFilter {

  private boolean postOnly = true;

  private final String processUrl;

  private final String paramName;

  protected MobileAuthenticationFilter(String processUrl, String paramName) {
    super(processUrl);
    this.processUrl = processUrl;
    this.paramName = paramName;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    if (postOnly && !POST.getName().equals(request.getMethod())) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }

    String mobile = obtainMobile(request, paramName);

    if (mobile == null) {
      mobile = "";
    }

    mobile = mobile.trim();

    MobileAuthenticationToken authRequest = new MobileAuthenticationToken(mobile);

    // Allow subclasses to set the "details" property
    setDetails(request, authRequest);

    return this.getAuthenticationManager().authenticate(authRequest);
  }

  /**
   * allow to have your own implementation
   * @param request
   * @param paramName
   * @return
   */
  protected String obtainMobile(HttpServletRequest request, String paramName) {
    return obtainParam(request, paramName);
  }

  /**
   * @param request
   * @param authRequest
   */
  protected void setDetails(HttpServletRequest request, MobileAuthenticationToken authRequest) {
    authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
  }


  /**
   * Defines whether only HTTP POST requests will be allowed by this filter. If set to
   * true, and an authentication request is received which is not a POST request, an
   * exception will be raised immediately and authentication will not be attempted. The
   * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
   * authentication.
   * <p>
   * Defaults to <tt>true</tt> but may be overridden by subclasses.
   */
  @Autowired
  public void setPostOnly(boolean postOnly) {
    this.postOnly = postOnly;
  }

  public final String getMobileParameter() {
    return paramName;
  }


}
