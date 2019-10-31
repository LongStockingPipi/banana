package pers.jason.db;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 15:52
 */
public abstract class BananaDataSource extends BasicDataSource {

  protected final String URL_PREFIX = "jdbc:h2:file:";

  protected final String URL_SUFFIX = ";IGNORECASE=TRUE";

  protected final String DRIVER_H2 = "org.h2.Driver";
}
