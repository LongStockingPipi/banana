package pers.jason.db;

import com.google.inject.Guice;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pers.jason.db.support.Properties;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 16:23
 */
public class QueryRunnerTest {

  @Inject
  private QueryRunner queryRunner;

  @Inject
  private DatabaseOperator operator;

  private final ResultSetHandler<Void> handler = rs -> {
    while(rs.next()) {
      String tableName = rs.getString(1);
      System.out.println(tableName);
      assertEquals("TB_1", tableName);
      break;
    }
    return null;
  };

  @Before
  public void setUp() {
    Properties properties = new Properties();
    properties.put("h2.path", "D:/files/banana");
    Guice.createInjector(new DatabaseModule(properties)).injectMembers(this);
  }

  @Test
  public void testForQuery() throws SQLException {
    assertNotNull(queryRunner);
    assertNotNull(operator);
    queryRunner.query("show tables", this.handler);
  }

}
