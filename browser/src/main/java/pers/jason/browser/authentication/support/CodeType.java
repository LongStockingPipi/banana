package pers.jason.browser.authentication.support;

/**
 * @author 姜治昊
 */

public enum CodeType {

  sms("sms", 1),
  image("image", 2),
  slider("slider", 3);

  private String name;

  private Integer code;

  CodeType(String name, Integer code) {
    this.name = name;
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
