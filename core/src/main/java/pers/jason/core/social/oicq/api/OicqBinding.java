package pers.jason.core.social.oicq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @Author 姜治昊
 * @Description a component for requesting user information from a third-party server
 * @Date 2019/11/5 15:39
 */
public class OicqBinding extends AbstractOAuth2ApiBinding implements Oicq {

  private static final Logger logger = LoggerFactory.getLogger(OicqBinding.class);

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Common parameters of the OAuth2.0 protocol: appId assigned to the app
   */
  private final String appId;

  /**
   * Common parameters of the OAuth2.0 protocol: the ID of the user, which corresponds to the third-party application user ID.
   */
  private final String openId;

  public OicqBinding(String accessToken, String appId) {
    super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    this.appId = appId;
    String url = String.format(REQUEST_OPENID_URL_GET, accessToken);
    logger.info("request use openId ...");
    String result = getRestTemplate().getForObject(url, String.class);
    this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    logger.info("openId: {}", this.openId);
  }

  @Override
  public OicqUser getOicqUserInfo() {
    String url = String.format(REQUEST_USER_INFO_URL_GET, appId, openId);

    logger.info("request user info ...");
    String result = getRestTemplate().getForObject(url, String.class);
    try {
      OicqUser user = objectMapper.readValue(result, OicqUser.class);
      user.setOpenId(openId);
      return user;
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      logger.error("request user info failed: {}", e.getMessage());
      throw new RuntimeException("request user info failed!");
    }
  }
}
