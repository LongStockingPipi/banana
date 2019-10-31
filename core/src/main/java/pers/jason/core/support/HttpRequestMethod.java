package pers.jason.core.support;

/**
 * @author 姜治昊
 */

public enum HttpRequestMethod {

  POST("POST"),
  GET("GET"),
  PUT("PUT"),
  DELETE("DELETE");

  private String name;

  HttpRequestMethod(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
