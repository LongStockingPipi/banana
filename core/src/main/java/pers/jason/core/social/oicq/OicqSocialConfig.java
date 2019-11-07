package pers.jason.core.social.oicq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;
import pers.jason.core.property.BananaProperties;
import pers.jason.core.property.SocialChannel;
import pers.jason.core.social.oicq.connect.OicqConnectionFactory;
import pers.jason.core.social.view.ConnectResponseView;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 16:11
 */
@Configuration
//@ConditionalOnProperty(prefix = "banana.security", name="social.socials.oicq")
public class OicqSocialConfig extends SocialAutoConfigurerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(OicqSocialConfig.class);

  @Autowired
  private BananaProperties bananaProperties;

  public OicqSocialConfig() {
    logger.info("OicqSocialConfig init ...");
  }

  @Override
  protected ConnectionFactory<?> createConnectionFactory() {
    SocialChannel oicqChannel = bananaProperties.getSocial().getSocials().get("oicq");
    String providerId = oicqChannel.getProviderId();
    String appId = oicqChannel.getAppId();
    String appSecret = oicqChannel.getAppSecret();
    return new OicqConnectionFactory(providerId, appId, appSecret);
  }

  /**
   * 绑定/解绑返回的视图
   * @return
   */
  @Bean({"connect/oicq"})
  public View ConnectResponseView() {
    return new ConnectResponseView();
  }
}
