package kr.intraview.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.intraview.mapper.UserMapper;
import kr.intraview.model.User;
import kr.intraview.model.UserDTO;

@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public void createUser(UserDTO userDto) {
    User user = new User(
      UUID.randomUUID().toString(),
      userDto.getEmail(),
      userDto.getPassword()
    );
    userMapper.insertUser(user);
  }

}
