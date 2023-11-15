package kr.intraview.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.intraview.exception.EmailNotFoundException;
import kr.intraview.model.User;
import kr.intraview.model.UserDetailsImpl;
import kr.intraview.service.UserService;

public class AuthenticationProviderImpl implements AuthenticationProvider {

  private PasswordEncoder passwordEncoder;
  private UserService userService;

  public AuthenticationProviderImpl() {
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(
    Authentication authentication
  ) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    try {
      User user = userService.loadUserByEmail(email);
      if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new BadCredentialsException(
          "Invalid credentials for email: " + email
        );
      }

      UserDetails userDetails = new UserDetailsImpl(user);

      return new UsernamePasswordAuthenticationToken(
        userDetails,
        password,
        userDetails.getAuthorities()
      );
    } catch (EmailNotFoundException e) {
      throw new UsernameNotFoundException(e.getMessage());
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class
      .isAssignableFrom(authentication);
  }

}
