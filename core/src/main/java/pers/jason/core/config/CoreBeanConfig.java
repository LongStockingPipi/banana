package pers.jason.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.jason.db.H2EmbeddedDataSource;

import javax.sql.DataSource;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/8 14:24
 */
@Configuration
public class CoreBeanConfig {

  @Bean(destroyMethod = "")
  @ConditionalOnMissingBean({DataSource.class})
  public H2EmbeddedDataSource h2EmbeddedDataSource() {
    final String filePath = "core/db/banana";
    return new H2EmbeddedDataSource(filePath);
  }

}
