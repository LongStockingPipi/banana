package pers.jason.browser.authentication.authtype.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 10:53
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

  private UserDetailsService userDetailsService;

  public MobileAuthenticationProvider(UserDetailsService detailsService) {
    this.userDetailsService = detailsService;
  }

  /**
   * Authentication logic
   * @param authentication
   * @return
   * @throws AuthenticationException
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken) authentication;

    String principal = (String) authenticationToken.getPrincipal();
    UserDetails details = userDetailsService.loadUserByUsername(principal);

    if(null == details) {
      throw new InternalAuthenticationServiceException("unable to get user information: " + principal);
    }

    //Repackage token
    MobileAuthenticationToken newToken = new MobileAuthenticationToken(details, details.getAuthorities());
    newToken.setDetails(authenticationToken.getDetails());
    return newToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return MobileAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
