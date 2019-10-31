package pers.jason.db;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.commons.dbutils.QueryRunner;
import pers.jason.db.support.Properties;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 16:13
 */
public class DatabaseModule extends AbstractModule {

  private final Properties properties;

  public DatabaseModule(Properties properties) {
    this.properties = properties;
  }

  @Override
  protected void configure() {
    Properties properties = new Properties();
    properties.put("h2.path", "D:\\files\\banana");
    bind(BananaDataSource.class).toInstance(new H2DataSource(properties));
  }

  @Provides
  public QueryRunner createQueryRunner(final BananaDataSource dataSource) {
    return new QueryRunner(dataSource);
  }

}
