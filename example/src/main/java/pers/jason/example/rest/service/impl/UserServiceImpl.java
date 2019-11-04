package pers.jason.example.rest.service.impl;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.jason.example.entity.User;
import pers.jason.example.exception.AuthenticationFailedException;
import pers.jason.example.rest.service.UserService;

import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/29 10:11
 */
@Service
public class UserServiceImpl implements UserService {


  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private static final Map<String, User> usernameAndPassWd;

  private static final Map<String, User> mobileAndUser;

  static {
    usernameAndPassWd = Maps.newHashMap();
    User user1 = new User();
    user1.setCnName("姜治昊");
    user1.setEnName("Jason");
    user1.setId(10011L);
    user1.setEmail("592623528@qq.com");
    user1.setUsername("qwert");
    user1.setPassword("123456");
    usernameAndPassWd.put("qwert", user1);

    User user2 = new User();
    user2.setCnName("小明");
    user2.setEnName("Xiaoming");
    user2.setId(10012L);
    user2.setEmail("474655132@qq.com");
    user2.setUsername("asdfg");
    user2.setPassword("234567");
    usernameAndPassWd.put("asdfg", user2);

    mobileAndUser = Maps.newHashMap();
    User user3 = new User();
    user3.setCnName("小刚");
    user3.setEnName("Xiaogang");
    user3.setId(10013L);
    user3.setEmail("1234qwer@qq.com");
    user3.setTel("18321843010");

    mobileAndUser.put("18321843010", user3);
  }

  @Override
  public User findUserByUsername(String username) {
    logger.debug("auth request: " + username);
    if(StringUtils.isEmpty(username)) {
      throw new AuthenticationFailedException("username can not be null");
    }

    User user = usernameAndPassWd.get(username);
    if(null == user || null == user.getId()) {
      throw new AuthenticationFailedException("no user username is '" + username + " '");
    }

    return user;
  }

  @Override
  public User findByUsernameOrPhoneNumber(String s) {
    logger.debug("auth request: " + s);

    if(StringUtils.isEmpty( s)) {
      return null;
    }
    User user = usernameAndPassWd.get(s);
    if(null == user) {
      user = mobileAndUser.get(s);
    }
    return user;
  }


}
