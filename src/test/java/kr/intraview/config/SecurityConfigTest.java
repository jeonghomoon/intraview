package kr.intraview.config;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import kr.intraview.exception.EmailNotFoundException;
import kr.intraview.model.User;
import kr.intraview.service.UserService;

@WebMvcTest(SecurityConfig.class)
public class SecurityConfigTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  PasswordEncoder passwordEncoder;

  @MockBean
  UserService userService;

  @Test
  public void testFormLogin() throws Exception {
    // given
    String email = "jeonghomoon@yahoo.com";
    String password = "1q2w3e4r1!";
    User user = User.builder()
      .email(email)
      .password(passwordEncoder.encode(password).toString())
      .build();
    when(userService.loadUserByEmail(email)).thenReturn(user);

    // when & then
    mockMvc.perform(post("/users/login")
      .param("email", email)
      .param("password", password)
      .with(csrf()))
      .andExpect(redirectedUrl("/interviews"));
  }

  @Test
  public void testFormLoginThrownUsernameNotFoundException() throws Exception {
    // given
    String email = "jeonghomoon@yahoo.com";
    String password = "1q2w3e4r1!";
    when(userService.loadUserByEmail(email)).thenThrow(EmailNotFoundException.class);

    // when & then
    mockMvc.perform(post("/users/login")
      .param("email", email)
      .param("password", password)
      .with(csrf()))
      .andExpect(forwardedUrl("/login"));
  }

  @Test
  public void testFormLoginThrownBadCredentialsException() throws Exception {
    // given
    String email = "jeonghomoon@yahoo.com";
    String password = "1q2w3e4r1!";
    User user = User.builder().email(email).password(password).build();
    when(userService.loadUserByEmail(email)).thenReturn(user);

    // when & then
    mockMvc.perform(post("/users/login")
      .param("email", email)
      .param("password", password)
      .with(csrf()))
      .andExpect(forwardedUrl("/login"));
  }

}
