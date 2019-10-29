package pers.jason.example.exception;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 15:30
 */
public class ForbiddenException extends RuntimeException {

  private static final long serialVersionUID = 7981455994586790234L;

  public ForbiddenException() {
    super();
  }

  public ForbiddenException(String message) {
    super(message);
  }
}
