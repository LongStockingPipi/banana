package pers.jason.example.exception;

import pers.jason.example.dto.response.ResponseCode;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 15:36
 */
public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = -687521238444426989L;

  private String code;

  /**
   * legacy support, new APIs should not call this. Instead, new APIs should provide return code
   */
  public BadRequestException(String msg) {
    super(msg);
    this.code = ResponseCode.CODE_UNDEFINED;
  }

  public BadRequestException(String msg, String code) {
    super(msg);
    this.code = code;
  }

  public BadRequestException(String msg, String code, Throwable cause) {
    super(msg, cause);
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
