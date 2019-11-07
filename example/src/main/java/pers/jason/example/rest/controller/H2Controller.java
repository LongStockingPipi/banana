package pers.jason.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/7 15:06
 */
@RestController
public class H2Controller {

  @Autowired
  private DataSource dataSource;

  @GetMapping("conusers")
  public Object getConnectionUsers() {
    String sql = "select * from banana_userconnection";
    List<Map<String, Object>> list = new ArrayList<>();
    try(
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)
        ) {
      ResultSetMetaData rsmd = resultSet.getMetaData();
      while(resultSet.next()) {
        Map map = new HashMap();
        int columnCount = rsmd.getColumnCount();
        for(int i=0;i<columnCount;i++){
          String columnName = rsmd.getColumnName(i+1);
          map.put(columnName, resultSet.getObject(i+1));
        }
        list.add(map);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
}
