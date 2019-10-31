package pers.jason.browser.exception;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/30 10:41
 */
public class ValidateCodeException extends RuntimeException {

  public static final Integer EXPIRED = 2;

  public static final Integer EMPTY_IN_REQUEST = 3;

  public static final Integer NULL_IN_CACHE = 4;

  public static final Integer MISMATCH = 5;

  public static final Integer GET_EXCEPTION = 6;

  private final Integer code;

  public ValidateCodeException(String msg, Integer code) {
    super(msg);
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }
}
