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
   * 短信验证码长度
   */
  private Integer smsCodeLength = 6;

  /**
   * 验证码参数名
   */
  private String validateCodeParamName = "validate_code";

  /**
   * 校验码的过期时间，单位是秒
   */
  private Long expireTime = 600L;

  /**
   * 手机号参数
   */
  private String mobileNumParamName = "mobile";

  private Boolean needCaptcha = true;

  private Integer imageCaptchaWidth = 118;

  private Integer imageCaptchaHeight = 42;

  private String captchaType="image";


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

  public String getAuthType() {
    return authType;
  }

  public void setAuthType(String authType) {
    this.authType = authType;
  }

  public Boolean getNeedCaptcha() {
    return needCaptcha;
  }

  public void setNeedCaptcha(Boolean needCaptcha) {
    this.needCaptcha = needCaptcha;
  }

  public Integer getImageCaptchaHeight() {
    return imageCaptchaHeight;
  }

  public void setImageCaptchaHeight(Integer imageCaptchaHeight) {
    this.imageCaptchaHeight = imageCaptchaHeight;
  }

  public Integer getImageCaptchaWidth() {
    return imageCaptchaWidth;
  }

  public void setImageCaptchaWidth(Integer imageCaptchaWidth) {
    this.imageCaptchaWidth = imageCaptchaWidth;
  }

  public String getCaptchaType() {
    return captchaType;
  }

  public void setCaptchaType(String captchaType) {
    this.captchaType = captchaType;
  }
}
