package pers.jason.browser.authentication.authtype.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 9:35
 */
public class MobileAuthenticationToken extends AbstractAuthenticationToken {

  private final Object mobileNum;

  public MobileAuthenticationToken(String mobileNum) {
    super(null);
    this.mobileNum = mobileNum;
    setAuthenticated(false);
  }

  /**
   * Creates a token with the supplied array of authorities.
   *
   * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
   *                    represented by this authentication object.
   */
  public MobileAuthenticationToken(Object mobileNum, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.mobileNum = mobileNum;
    super.setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return this.mobileNum;
  }

}
