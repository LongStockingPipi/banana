package pers.jason.db;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 17:03
 */
public class H2dbTest {

  public static class EmbeddedH2BasicDataSource extends BananaDataSource {

    @Before
    public void setUp() {
    }

    @Test
    public void testConnect() throws SQLException, ClassNotFoundException {
      Class.forName("org.h2.Driver");
      Connection conn = DriverManager.
          getConnection("jdbc:h2:file:D:\\files\\banana;IGNORECASE=TRUE");
      // add application code here
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("show tables");
      while (rs.next()) {
        String tbName = rs.getString(1);
        System.out.println(tbName);
      }
      conn.close();
    }
  }

}
