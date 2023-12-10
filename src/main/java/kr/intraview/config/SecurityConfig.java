package kr.intraview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import kr.intraview.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private UserService userService;

  @Autowired
  public void setUserService(@Lazy UserService userService) {
    this.userService = userService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(
    HttpSecurity http
  ) throws Exception {
    http
      .authenticationProvider(authenticationProvider())
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/register", "/login").permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(form -> form
        .loginPage("/login")
        .usernameParameter("email")
        .loginProcessingUrl("/users/login")
        .defaultSuccessUrl("/interviews", true)
        .failureHandler(authenticationFailureHandler())
        .permitAll());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    EmailAuthenticationProvider authProvider = new EmailAuthenticationProvider();
    authProvider.setPasswordEncoder(passwordEncoder());
    authProvider.setUserService(userService);
    return authProvider;
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    AuthenticationFailureHandlerImpl authFailureHandler = new AuthenticationFailureHandlerImpl();
    return authFailureHandler;
  }

}
