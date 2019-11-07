package pers.jason.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;
import pers.jason.core.property.BananaProperties;
import pers.jason.core.property.SocialChannel;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 16:32
 */
@Order(1)
@EnableSocial
@Configuration
public class SocialConfig extends SocialConfigurerAdapter {


  @Autowired
  private DataSource dataSource;

  @Autowired
  private BananaProperties bananaProperties;

  @Autowired(required = false)
  private ConnectionSignUp connectionSignUp;

  private final String tb_prefix = "banana_";

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
    JdbcUsersConnectionRepository jdbcUsersConnectionRepository =
        new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    jdbcUsersConnectionRepository.setTablePrefix(tb_prefix);
    if(null != connectionSignUp) {
      jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
    }
    return jdbcUsersConnectionRepository;
  }

  /**
   * 支持社交登陆方式的配置类
   * @return
   */
  @Bean
  public SpringSocialConfigurer socialSecurityConfig() {
    Map<String, SocialChannel> channels = bananaProperties.getSocial().getSocials();
    SocialChannel oicq = channels.get("oicq");

    SpringSocialConfigurer configure = new DefaultSpringSocialConfig(oicq.getProcessUrls().get(0));
    configure.signupUrl(bananaProperties.getSocial().getSignUpPage());
    return configure;
  }

  /**
   * When you register, you can get social information (user information obtained from the service provider)
   * After registration, pass the user ID to social to create a social account (userconnection table)
   * @param connectionFactoryLocator
   * @return
   */
  @Bean
  public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
    return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
  }
}
