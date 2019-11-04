package pers.jason.core.property;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/4 16:15
 */
public class ImageCaptchaProperties {

  private Integer length = 6;

  private Integer expireIn = 60;

  private Integer width = 118;

  private Integer height = 42;

  private List<String> uri = new ArrayList<>();

  private String requestUri = "/captcha";

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Integer getExpireIn() {
    return expireIn;
  }

  public void setExpireIn(Integer expireIn) {
    this.expireIn = expireIn;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public List<String> getUri() {
    return uri;
  }

  public void setUri(List<String> uri) {
    this.uri = uri;
  }

  public String getRequestUri() {
    return requestUri;
  }

  public void setRequestUri(String requestUri) {
    this.requestUri = requestUri;
  }
}
