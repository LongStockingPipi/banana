package pers.jason.core.social.oicq.api;

/**
 * @author 姜治昊
 */
public interface Oicq {

  /**
   * Application authorization code（openId）
   */
  String REQUEST_OPENID_URL_GET = "https://graph.qq.com/oauth2.0/me?access_token=%s";

  /**
   * Apply for user information
   */
  String REQUEST_USER_INFO_URL_GET = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

  OicqUser getOicqUserInfo();

}
