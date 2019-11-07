package pers.jason.core.social.oicq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import pers.jason.core.social.oicq.api.Oicq;
import pers.jason.core.social.oicq.api.OicqBinding;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 15:59
 */
public class OicqServiceProvider extends AbstractOAuth2ServiceProvider<Oicq> {

  private final String appId;

  private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";

  private static final String ACCESS_TO_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";

  public OicqServiceProvider(String appId, String appSecret) {
    super(new OicpOAuth2Template(appId, appSecret, AUTHORIZE_URL, ACCESS_TO_TOKEN_URL));
    this.appId = appId;
  }

  @Override
  public Oicq getApi(String accessToken) {
    return new OicqBinding(accessToken, appId);
  }
}
