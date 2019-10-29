package pers.jason.example.exception;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 15:35
 */
public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = 4251335901646470391L;

  public NotFoundException() {
    super();
  }

  public NotFoundException(String message) {
    super(message);
  }
}
