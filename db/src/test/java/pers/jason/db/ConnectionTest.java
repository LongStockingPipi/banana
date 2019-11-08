package pers.jason.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/8 14:47
 */
public class ConnectionTest {

  private H2EmbeddedDataSource dataSource;

  @BeforeEach
  public void setUp() {
    this.dataSource = new H2EmbeddedDataSource("db/test_db");
  }

  @Test
  public void testForConnection() {
    try (
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("show tables")) {
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testForCreateTable() {
    final String create_table_1 = "create table tb_1(tb_id bigint, content varchar(255));";
    try (
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
      boolean result = statement.execute(create_table_1);
      System.out.println(result);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testForInsert() {
    final String insert_1 = "insert into tb_1 values (1001, 'asdfasdfasdf')";
    try (
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
      boolean result = statement.execute(insert_1);
      System.out.println(result);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testForSearch() {
    final String search_1 = "select tb_id, content from tb_1";
    try (
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(search_1)
    ) {
      while (resultSet.next()) {
        Long id = resultSet.getLong("tb_id");
        String content = resultSet.getString("content");
        System.out.println("id:" + id + "; content:" + content);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testForDelete() {
    final String delete_1 = "delete from tb_1 where tb_id = 1001";
    try (
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
      boolean result = statement.execute(delete_1);
      System.out.println(result);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
