package pers.jason.example.exception;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:29
 */
public class AuthenticationFailedException extends RuntimeException {


  private static final long serialVersionUID = -1689930041885797253L;

  public AuthenticationFailedException() {
    super();
  }

  public AuthenticationFailedException(String message) {
    super(message);
  }
}
