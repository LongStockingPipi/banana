package pers.jason.core.social.oicq.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import pers.jason.core.social.oicq.api.Oicq;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 16:16
 */
public class OicqConnectionFactory extends OAuth2ConnectionFactory<Oicq> {

  public OicqConnectionFactory(String providerId, String appId, String appSecret) {
    super(providerId, new OicqServiceProvider(appId, appSecret), new OicqAdapter());
  }
}
