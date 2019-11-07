package pers.jason.core.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 16:36
 */
public class DefaultSpringSocialConfig extends SpringSocialConfigurer {

  private static final Logger logger = LoggerFactory.getLogger(DefaultSpringSocialConfig.class);

  private final String filterProcessesUrl;

  public DefaultSpringSocialConfig(String filterProcessesUrl) {
    logger.info("social filter process uri : " + filterProcessesUrl);
    this.filterProcessesUrl = filterProcessesUrl;
  }

  @Override
  protected <T> T postProcess(T object) {
    // TODO Auto-generated method stub
    SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
    filter.setFilterProcessesUrl(filterProcessesUrl);
    return (T) filter;
  }
}
