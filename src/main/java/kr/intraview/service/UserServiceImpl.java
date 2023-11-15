package kr.intraview.service;

import java.util.UUID;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.intraview.exception.DuplicateEmailException;
import kr.intraview.exception.EmailNotFoundException;
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

  @Override
  public void createUser(UserDTO userDto) throws DuplicateEmailException {
    String encodedPassword = passwordEncoder.encode(userDto.getPassword());
    User user = new User(
      UUID.randomUUID().toString(),
      userDto.getEmail(),
      encodedPassword
    );

    try {
      userMapper.insertUser(user);
    } catch (DuplicateKeyException e) {
      throw new DuplicateEmailException("");
    }
  }

  @Override
  public User loadUserByEmail(String email) throws EmailNotFoundException {
    User user = userMapper.findByEmail(email);
    if (user == null) {
      throw new EmailNotFoundException("");
    }
    return user;
  }

}
