package pers.jason.example.dto.response;

/**
 * @Author 姜治昊
 * @Description It's not HTTP return code! It represents code for business states
 *              each API can specify different semantics to each code
 *              000 and 999 are reserved
 * @Date 2019/10/28 15:37
 */
public class ResponseCode {

  public final static String CODE_SUCCESS = "000";
  public final static String CODE_ERROR = "001";
  public final static String CODE_SQL_PARSE_ERROR = "002";
  public final static String CODE_SQL_AUTHORIZATION_ERROR = "003";
  public final static String CODE_UNDEFINED = "999";
}
