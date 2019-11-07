package pers.jason.example.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import pers.jason.example.entity.User;
import pers.jason.example.rest.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/7 13:53
 */
@RestController
@RequestMapping("user")
public class UserController {

  private Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private ProviderSignInUtils providerSignInUtils;

  @Autowired
  private UserService userService;

  @PostMapping("signup")
  public void registered(User user, HttpServletRequest request) {
    logger.info("user registered: " + user.getCnName());
    user.setId(System.currentTimeMillis());
    providerSignInUtils.doPostSignUp(user.getId() + "", new ServletWebRequest(request));
  }

  @PostMapping("binding")
  public void binding( User user, HttpServletRequest request) {
    String username = user.getUsername();
    String password = user.getPassword();
    logger.info("user binding: " + username);
    User user_ = userService.findUserByUsername(username);
    if(user_.getPassword().equals(password)) {
      providerSignInUtils.doPostSignUp(user_.getId() + "", new ServletWebRequest(request));
    }
  }
}
