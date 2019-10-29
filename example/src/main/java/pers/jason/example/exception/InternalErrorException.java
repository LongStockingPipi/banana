package pers.jason.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 15:41
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends RuntimeException {

  private static final long serialVersionUID = -2608161486211813544L;

  public InternalErrorException(String s) {
    super(s);
  }

  /**
   *
   */
  public InternalErrorException() {
    super();
  }

  /**
   * @param arg0
   * @param arg1
   */
  public InternalErrorException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  /**
   * @param arg0
   */
  public InternalErrorException(Throwable arg0) {
    super(arg0);
  }
}
