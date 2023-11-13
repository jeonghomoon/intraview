package kr.intraview.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import kr.intraview.SecurityConfig;
import kr.intraview.service.UserService;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserService userService;

  @Test
  public void testRegisterUser() throws Exception {
    // given
    String email = "jeonghomoon@yahoo.com";
    String password = "1q2w3e4r1!";

    // when & then
    mockMvc.perform(post("/users/register")
      .with(csrf())
      .param("email", email)
      .param("password", password))
      .andExpect(redirectedUrl("/login"));
  }

}
