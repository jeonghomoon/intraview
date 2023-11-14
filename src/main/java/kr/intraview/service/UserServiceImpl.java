package kr.intraview.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.intraview.mapper.UserMapper;
import kr.intraview.model.User;
import kr.intraview.model.UserDTO;

@Service
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public UserServiceImpl(
    PasswordEncoder passwordEncoder,
    UserMapper userMapper
  ) {
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  public void createUser(UserDTO userDto) {
    String encodedPassword = passwordEncoder.encode(userDto.getPassword());
    User user = new User(
      UUID.randomUUID().toString(),
      userDto.getEmail(),
      encodedPassword
    );
    userMapper.insertUser(user);
  }

  public User loadUserByEmail(String email) {
    return userMapper.findByEmail(email);
  }

}
