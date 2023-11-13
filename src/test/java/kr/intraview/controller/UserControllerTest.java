package kr.intraview.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import kr.intraview.service.UserService;

@WebMvcTest(UserController.class)
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
      .param("email", email)
      .param("password", password))
      .andExpect(redirectedUrl("/login"));
  }

}
