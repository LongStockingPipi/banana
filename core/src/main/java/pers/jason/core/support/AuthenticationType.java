package pers.jason.core.support;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 9:04
 */
public enum AuthenticationType {

  /**
   * mobile num + SMS
   */
  mobile(1, "mobile"),

  /**
   * username + passwd (maybe need captcha)
   */
  account(2, "account");

  private Integer code;

  private String name;

  AuthenticationType(Integer code, String name) {
    this.code = code;
    this.name = name;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
