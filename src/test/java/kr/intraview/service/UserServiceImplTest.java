package kr.intraview.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.intraview.exception.DuplicateEmailException;
import kr.intraview.exception.EmailNotFoundException;
import kr.intraview.mapper.UserMapper;
import kr.intraview.model.UserDTO;
import kr.intraview.model.User;

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
    UserDTO userDto = UserDTO.builder()
      .email("jeonghomoon@yahoo.com")
      .password("1q2w3e4r1!")
      .build();

    // when & then
    assertDoesNotThrow(() -> userService.createUser(userDto));
    verify(userMapper, times(1)).insertUser(any());
  }

  @Test
  public void testCreateUserThrownDuplicateEmailException() {
    // given
    UserDTO userDto = UserDTO.builder()
      .email("jeonghomoon@yahoo.com")
      .password("1q2w3e4r1!")
      .build();
    doThrow(DuplicateKeyException.class).when(userMapper).insertUser(any());

    // when & then
    assertThrows(DuplicateEmailException.class, () -> {
      userService.createUser(userDto);
    });
    verify(userMapper, times(1)).insertUser(any());
  }

  @Test
  public void testLoadUserByEmail() {
    // given
    String email = "jeonghomoon@yahoo.com";
    when(userMapper.findByEmail(any())).thenReturn(User.builder().build());

    // when & then
    assertDoesNotThrow(() -> userService.loadUserByEmail(email));
    verify(userMapper, times(1)).findByEmail(any());
  }

  @Test
  public void testLoadUserByEmailThrownEmailNotFoundException() {
    // given
    String email = "jeonghomoon@yahoo.com";
    when(userMapper.findByEmail(any())).thenReturn(null);

    // when & then
    assertThrows(EmailNotFoundException.class, () -> {
      userService.loadUserByEmail(email);
    });
    verify(userMapper, times(1)).findByEmail(any());
  }

}
