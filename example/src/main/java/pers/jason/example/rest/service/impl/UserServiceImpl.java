package pers.jason.example.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.jason.example.entity.User;
import pers.jason.example.rest.service.UserService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/29 10:11
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private DataSource dataSource;


  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);



  @Override
  public User findUserByUsername(String username) {
    String sql = "select u_id, u_cn_name, u_password from banana_user where u_username = '" + username + "' limit 1";
    User user = null;
    try(
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ) {
      if(resultSet.next()) {
        Long id = resultSet.getLong("u_id");
        String u_zn_name = resultSet.getString("u_cn_name");
        String password = resultSet.getString("u_password");
        user = new User();
        user.setId(id);
        user.setCnName(u_zn_name);
        user.setUsername(username);
        user.setPassword(password);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return user;
  }

  @Override
  public User findByUsernameOrPhoneNumber(String s) {
    logger.debug("auth request: " + s);

    if(StringUtils.isEmpty( s)) {
      return null;
    }
    User user = findUserByUsername(s);
    if(null == user) {
      String sql = "select u_id, u_cn_name, u_username, u_password from banana_user where u_tel = '" + s + "' limit 1";
      try(
          Connection connection = dataSource.getConnection();
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(sql);
      ) {
        if(resultSet.next()) {
          Long id = resultSet.getLong("u_id");
          String u_zn_name = resultSet.getString("u_cn_name");
          String username = resultSet.getString("u_username");
          String password = resultSet.getString("u_password");
          user = new User();
          user.setId(id);
          user.setUsername(username);
          user.setCnName(u_zn_name);
          user.setPassword(password);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return user;
  }

  @Override
  public void addUser(User user) {
//    usernameAndPassWd.put(user.getUsername(), user);
  }

  @Override
  public String loadUserByUserId(Long id) {
    String sql = "select u_password from banana_user where u_id = " + id + " limit 1";
    try(
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
    ) {
      if(resultSet.next()) {
        String password = resultSet.getString("u_password");
        return password;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }


}
