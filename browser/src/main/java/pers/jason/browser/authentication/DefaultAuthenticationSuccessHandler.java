package pers.jason.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import pers.jason.core.property.BananaProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pers.jason.browser.authentication.support.RequestType.ajax;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:09
 */
public class DefaultAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final ObjectMapper objectMapper;

  private final BananaProperties bananaProperties;

  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  private static final Logger logger = LoggerFactory.getLogger(DefaultAuthenticationSuccessHandler.class);

  public DefaultAuthenticationSuccessHandler(ObjectMapper objectMapper, BananaProperties bananaProperties) {
    super();
    this.objectMapper = objectMapper;
    this.bananaProperties = bananaProperties;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    logger.info("login success");

    final String authRequestType = bananaProperties.getAuth().getLoginRequestType();

    if(StringUtils.equals(ajax.getName(), authRequestType)) {

      //request with ajax
      AuthenticationResponse authenticationResponse
          = new AuthenticationResponse(true, "login success");

      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(authenticationResponse));
    } else {

      // request from page
      // redirect to login page with error message
      redirectStrategy.sendRedirect(request, response, bananaProperties.getWelcomePage());
    }
  }
}
