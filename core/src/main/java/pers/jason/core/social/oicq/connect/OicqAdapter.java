package pers.jason.core.social.oicq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import pers.jason.core.social.oicq.api.Oicq;
import pers.jason.core.social.oicq.api.OicqUser;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/5 16:18
 */
public class OicqAdapter implements ApiAdapter<Oicq> {
  @Override
  public boolean test(Oicq api) {
    return false;
  }

  @Override
  public void setConnectionValues(Oicq api, ConnectionValues values) {
// TODO Auto-generated method stub
    OicqUser userInfo = api.getOicqUserInfo();
    values.setDisplayName(userInfo.getNickname());
    values.setImageUrl(userInfo.getFigureurl_qq_1());
    values.setProfileUrl(null);
    values.setProviderUserId(userInfo.getOpenId());
  }

  @Override
  public UserProfile fetchUserProfile(Oicq api) {
    return null;
  }

  @Override
  public void updateStatus(Oicq api, String message) {

  }
}
