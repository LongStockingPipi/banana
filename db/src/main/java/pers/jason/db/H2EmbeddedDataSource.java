package pers.jason.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/8 14:06
 */
public class H2EmbeddedDataSource extends BasicDataSource {

  private final String filePath;

  public H2EmbeddedDataSource(String filePath) {
    super();
    this.filePath = filePath;
    final Path path = Paths.get(filePath).toAbsolutePath();
    final String url = "jdbc:h2:file:" + path + ";IGNORECASE=TRUE";
    System.out.println("url: " + url);
    setDriverClassName("org.h2.Driver");
    setUrl(url);

    //PoolableConnectionFactory
    setUsername("sa");
    setPassword("");
  }

  public String getFilePath() {
    return filePath;
  }
}
