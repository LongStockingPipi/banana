package pers.jason.core.social.view;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/7 15:39
 */
@Component("connect/oicq")
public class ConnectResponseView extends AbstractView {

  @Override
  protected void renderMergedOutputModel(
      Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=utf-8");
    String html = "<h3>";
    if(null == model.get("connection")) {
      html += "解绑成功";
    } else {
      html += "绑定成功";
    }
    html += "</h3>";
    response.getWriter().write(html);
  }


}
