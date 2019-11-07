package pers.jason.core.social.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/7 15:48
 */
@Component("status/oicq")
public class ConnectView extends AbstractView {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  protected void renderMergedOutputModel(
      Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    Map<String, List<Connection<?>>> connections = (Map<String, List<Connection<?>>>) map.get("connectionMap");
    Map<String, Boolean> result = new HashMap<>();
    for (String key : connections.keySet()) {
      result.put(key, CollectionUtils.isNotEmpty(connections.get(key)));
    }
    httpServletResponse.setContentType("application/json;charset=utf-8");
    httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
  }
}
