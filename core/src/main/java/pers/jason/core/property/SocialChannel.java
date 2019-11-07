package pers.jason.core.property;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 16:22
 */
public class SocialChannel {

  private String appId;

  private String appSecret;

  private String providerId;

  private List<String> processUrls = new ArrayList<>();

  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
  }

  public List<String> getProcessUrls() {
    return processUrls;
  }

  public void setProcessUrls(List<String> processUrls) {
    this.processUrls = processUrls;
  }
}
