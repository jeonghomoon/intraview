package kr.intraview.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.intraview.mapper.UserMapper;
import kr.intraview.model.UserDTO;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  @Mock
  PasswordEncoder passwordEncoder;

  @Mock
  UserMapper userMapper;

  @InjectMocks
  UserServiceImpl userService;

  @Test
  public void testCreateUser() {
    // given
    UserDTO userDto = new UserDTO("jeonghomoon@yahoo.com", "1q2w3e4r1!");

    // when
    userService.createUser(userDto);

    // then
    verify(userMapper, times(1)).insertUser(any());
  }

}
