package pers.jason.db;

import com.google.common.base.Strings;
import pers.jason.db.support.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 15:55
 */
@Singleton
public class H2DataSource extends BananaDataSource {

  @Inject
  public H2DataSource(Properties properties) {

    String filePath = properties.get("h2.path");
    if(Strings.isNullOrEmpty(filePath)) {
      filePath = System.getProperty("user.dir") + "\\h2\\banana";
    }
    final Path h2DbPath = Paths.get(filePath).toAbsolutePath();
    final String url = URL_PREFIX + h2DbPath + URL_SUFFIX;

    setDriverClassName(DRIVER_H2);
    setUrl(url);
  }

}
