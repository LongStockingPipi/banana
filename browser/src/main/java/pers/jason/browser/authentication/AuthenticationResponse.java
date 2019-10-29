package pers.jason.browser.authentication;

import java.io.Serializable;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:31
 */
public class AuthenticationResponse implements Serializable {

  private static final long serialVersionUID = 6926199754841793138L;

  private String message;

  private Boolean success;

  public AuthenticationResponse(Boolean authSuccess) {
    this.success = authSuccess;
  }

  public AuthenticationResponse(Boolean authSuccess, String message) {
    this.success = authSuccess;
    this.message = message;
  }

  public Boolean wasSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }
}
