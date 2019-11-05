package pers.jason.browser.authentication.support;

public enum RequestType {

  ajax("ajax", 1L),

  form("form", 2L);

  private String name;

  private Long code;

  RequestType(String name, Long code) {
    this.name = name;
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public Long getCode() {
    return code;
  }
}
