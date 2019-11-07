package pers.jason.core.social.oicq.connect;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 16:02
 */
public class OicpOAuth2Template extends OAuth2Template {

  private static final Logger logger = LoggerFactory.getLogger(OicpOAuth2Template.class);

  public OicpOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
    super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
    setUseParametersForClientAuthentication(true);
  }

  @Override
  protected RestTemplate createRestTemplate() {
    // TODO Auto-generated method stub
    RestTemplate restTemplate = super.createRestTemplate();
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    return restTemplate;
  }

  @Override
  protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
    logger.info("request access token ...");
    String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
    String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
    String accessToken = StringUtils.substringAfterLast(items[0], "=");
    Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
    String refreshToken = StringUtils.substringAfterLast(items[2], "=");
    logger.info("access token:, {}", accessToken);
    return new AccessGrant(accessToken, null, refreshToken, expiresIn);
  }
}
