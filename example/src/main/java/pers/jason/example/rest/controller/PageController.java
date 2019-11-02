package pers.jason.example.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/29 8:36
 */
@Controller
@RequestMapping("page")
public class PageController {

  @GetMapping("index")
  public String gotoIndexPage() {
    return "index";
  }

  @GetMapping("login/{type}")
  public String gotoLoginPage(@PathVariable("type") String type) {
    return "login_" + type;
  }

}
