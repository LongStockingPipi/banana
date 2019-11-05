package pers.jason.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import pers.jason.browser.authentication.support.RequestType;
import pers.jason.core.property.BananaProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:10
 */
public class DefaultAuthenticationFailedHandler extends SimpleUrlAuthenticationFailureHandler {

  private final ObjectMapper objectMapper;

  private final BananaProperties bananaProperties;

  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  private static final Logger logger = LoggerFactory.getLogger(DefaultAuthenticationFailedHandler.class);

  public DefaultAuthenticationFailedHandler(ObjectMapper objectMapper, BananaProperties bananaProperties) {
    super();
    this.objectMapper = objectMapper;
    this.bananaProperties = bananaProperties;
  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
      , AuthenticationException exception) throws IOException, ServletException {

    final String exceptionMessage = exception.getMessage();
    logger.info("login failed {}", exceptionMessage);

    final String authRequestType = bananaProperties.getAuth().getLoginRequestType();

    if(StringUtils.equals(RequestType.ajax.getName(), authRequestType)) {

      //request with ajax
      AuthenticationResponse authenticationResponse
          = new AuthenticationResponse(false, exceptionMessage);

      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(authenticationResponse));
    } else {

      // request from page
      // redirect to login page with error message
      redirectStrategy.sendRedirect(request, response, bananaProperties.getAuth().getLoginPage());
    }
  }
}
