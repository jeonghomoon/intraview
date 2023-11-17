package kr.intraview.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import kr.intraview.exception.DuplicateEmailException;
import kr.intraview.config.SecurityConfig;
import kr.intraview.service.UserService;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserService userService;

  @Test
  public void testShowRegisterPage() throws Exception {
    // given & when & then
    mockMvc.perform(get("/users/register"))
      .andExpect(view().name("register"))
      .andExpect(model().attributeExists("user"));
  }

  @Test
  public void testRegisterUser() throws Exception {
    // given
    String email = "jeonghomoon@yahoo.com";
    String password = "1q2w3e4r1!";

    // when & then
    mockMvc.perform(post("/users/register")
      .param("email", email)
      .param("password", password)
      .with(csrf()))
      .andExpect(redirectedUrl("/login"));
  }

  @Test
  public void testRegisterUserThrownDuplicateEmailException() throws Exception {
    // given
    String email = "jeonghomoon@yahoo.com";
    String password = "1q2w3e4r1!";
    doThrow(DuplicateEmailException.class).when(userService).createUser(any());

    // when & then
    mockMvc.perform(post("/users/register")
      .param("email", email)
      .param("password", password)
      .with(csrf()))
      .andExpect(view().name("register"))
      .andExpect(model().attributeHasFieldErrors("user", "email"));
  }

}
