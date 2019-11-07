package pers.jason.example.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/7 17:13
 */
@Controller
public class SimpleController {

  @GetMapping("/")
  public String gotoIndex() {
    return "index";
  }
}
