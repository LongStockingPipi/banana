package pers.jason.core.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 17:17
 */
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

  /**
   * ajax请求的uri
   */
  private String requestUriWithAjax = "/";

  /**
   * 请求登录页面
   */
  private String loginPage = "/";

  /**
   * 首页
   */
  private String welcomePage = "/";

  /**
   * 登录请求
   */
  private String authRequestUri = "/";

  /**
   * 认证类型
   */
  private String authType = "account";

  /**
   * 是否开启校验码认证
   */
  private Boolean useCaptcha = false;

  /**
   * 短信验证码长度
   */
  private Integer smsCodeLength = 4;

  /**
   * 验证码参数名
   */
  private String validateCodeParamName = "validate_code";

  /**
   * 校验码的过期时间，单位是秒
   */
  private Long expireTime = 60L;

  /**
   * 手机号参数
   */
  private String mobileNumParamName = "auth_tel";


  public String getRequestUriWithAjax() {
    return requestUriWithAjax;
  }

  public void setRequestUriWithAjax(String requestUriWithAjax) {
    this.requestUriWithAjax = requestUriWithAjax;
  }

  public String getLoginPage() {
    return loginPage;
  }

  public void setLoginPage(String loginPage) {
    this.loginPage = loginPage;
  }

  public String getWelcomePage() {
    return welcomePage;
  }

  public void setWelcomePage(String welcomePage) {
    this.welcomePage = welcomePage;
  }

  public String getAuthRequestUri() {
    return authRequestUri;
  }

  public void setAuthRequestUri(String authRequestUri) {
    this.authRequestUri = authRequestUri;
  }


  public Integer getSmsCodeLength() {
    return smsCodeLength;
  }

  public void setSmsCodeLength(Integer smsCodeLength) {
    this.smsCodeLength = smsCodeLength;
  }

  public String getValidateCodeParamName() {
    return validateCodeParamName;
  }

  public void setValidateCodeParamName(String validateCodeParamName) {
    this.validateCodeParamName = validateCodeParamName;
  }

  public Long getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(Long expireTime) {
    this.expireTime = expireTime;
  }

  public String getMobileNumParamName() {
    return mobileNumParamName;
  }

  public void setMobileNumParamName(String mobileNumParamName) {
    this.mobileNumParamName = mobileNumParamName;
  }

  public Boolean getUseCaptcha() {
    return useCaptcha;
  }

  public void setUseCaptcha(Boolean useCaptcha) {
    this.useCaptcha = useCaptcha;
  }

  public String getAuthType() {
    return authType;
  }

  public void setAuthType(String authType) {
    this.authType = authType;
  }
}
