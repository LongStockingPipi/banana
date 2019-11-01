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
  mobile(1, "mobile", true),

  /**
   * username + passwd (maybe need captcha)
   */
  account(2, "account", false);

  private Integer code;

  private String name;

  private Boolean needCaptcha;

  AuthenticationType(Integer code, String name, Boolean needCaptcha) {
    this.code = code;
    this.name = name;
    this.needCaptcha = needCaptcha;
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

  public Boolean getNeedCaptcha() {
    return needCaptcha;
  }

  public void setNeedCaptcha(Boolean needCaptcha) {
    this.needCaptcha = needCaptcha;
  }
}
