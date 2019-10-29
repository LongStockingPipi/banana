package pers.jason.example.exception;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 15:38
 */
public class UnauthorizedException extends RuntimeException {

  private static final long serialVersionUID = -8686030604592839535L;

  public UnauthorizedException() {
    super();
  }

  public UnauthorizedException(String message) {
    super(message);
  }
}

