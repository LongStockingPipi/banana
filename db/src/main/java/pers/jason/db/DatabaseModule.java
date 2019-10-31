package pers.jason.db;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.jason.db.support.Properties;

import java.io.IOException;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 16:13
 */
public class DatabaseModule extends AbstractModule {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseOperator.class);

  private final String conf = "application.properties";

  private final Properties properties;

  public DatabaseModule(Properties properties) {
    this.properties = properties;
  }

  @Override
  protected void configure() {
    Properties properties = null;
    try {
      String conPath = System.getProperty("user.dir") + "\\db\\src\\main\\resources\\" + conf;
      properties = new Properties(null, conPath);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      logger.error("failed to load resource file");
    }
    bind(BananaDataSource.class).toInstance(new H2DataSource(properties));
  }

  @Provides
  public QueryRunner createQueryRunner(final BananaDataSource dataSource) {
    return new QueryRunner(dataSource);
  }

}
