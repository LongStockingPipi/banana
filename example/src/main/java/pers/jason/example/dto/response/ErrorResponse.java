package pers.jason.example.dto.response;

import java.io.Serializable;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 14:08
 */
public class ErrorResponse implements Serializable {

  private String message;

  private Object obj;

  private ErrorResponse() {}

  public static ErrorResponse createErrorResponse(Exception e, Object obj) {
    ErrorResponse response = new ErrorResponse();
    String exceptionMessage = e.getMessage();
    response.message = exceptionMessage;
    response.obj = null == obj ? e : obj;
    return response;
  }

}
