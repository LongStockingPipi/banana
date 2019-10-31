package pers.jason.db;

import com.google.inject.Guice;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.jason.db.pojo.User;
import pers.jason.db.support.Properties;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 16:23
 */
public class QueryRunnerTest {

  private static final Logger logger = LoggerFactory.getLogger(QueryRunnerTest.class);

  @Inject
  private DatabaseOperator operator;

  @Before
  public void setUp() throws IOException {
    Properties properties = new Properties(null, "D:\\WorkSpace\\2019-10\\banana\\db\\src\\main\\resources\\application.properties");
    Guice.createInjector(new DatabaseModule(properties)).injectMembers(this);
  }

  @Test
  public void testForCurd() throws SQLException {

    final String dropTable = "drop table if exists t_user;";
    operator.update(dropTable);

    final String createTable =
        "CREATE TABLE IF NOT EXISTS t_user(" +
        "   ID INT PRIMARY KEY, " +
        "   NAME VARCHAR(255)" +
        ");";
    operator.update(createTable);

    final String insertData1 = "" +
        "INSERT INTO t_user VALUES(1, 'Jason');";
    assert 1 == operator.update(insertData1);
    final String insertData2 = "" +
        "INSERT INTO t_user VALUES(2, 'Peter');";
    assert 1 == operator.update(insertData2);


    final String queryData1 = "" +
        "SELECT * FROM t_user ORDER BY ID;";
    List<User> result = operator.query(queryData1, new BeanListHandler<User>(User.class));
    assert result.size() == 2;
    for (User user : result) {
      assert (user.getId() == 1 ? "Jason".equals(user.getName()) : "Peter".equals(user.getName()));
    }



    final String queryData2 = "" +
        "select name from t_user where id = 2";
    assertEquals("Peter", operator.query(queryData2, rs -> {
      assert rs.next();
      return rs.getString(1);
    }));



    final String queryCount = "" +
        "select count(id) from t_user";
    assert 2 == operator.query(queryCount, new ScalarHandler<Long>());
  }

}
