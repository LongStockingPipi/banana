package pers.jason.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import pers.jason.core.property.BananaProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pers.jason.browser.authentication.support.RequestType.ajax;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 10:19
 */
public class DefaultSuccessSignOutHandler implements LogoutSuccessHandler {

  private static final Logger logger = LoggerFactory.getLogger(DefaultSuccessSignOutHandler.class);

  private final BananaProperties bananaProperties;

  private final ObjectMapper objectMapper;

  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  public DefaultSuccessSignOutHandler(BananaProperties bananaProperties, ObjectMapper objectMapper) {
    this.bananaProperties = bananaProperties;
    this.objectMapper = objectMapper;
  }

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response
      , Authentication authentication) throws IOException {

    logger.info("sign out success");

    if(StringUtils.equalsIgnoreCase(ajax.getName()
        , bananaProperties.getSignOut().getRequestType())) {
      AuthenticationResponse signOutResponse = new AuthenticationResponse(true);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(signOutResponse));
    } else {
      redirectStrategy.sendRedirect(request, response, bananaProperties.getAuth().getLoginPage());
    }

  }
}
