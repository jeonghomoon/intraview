package kr.intraview.service;

import kr.intraview.model.User;
import kr.intraview.model.UserDTO;

public interface UserService {

  public void createUser(UserDTO userDto);

  public User loadUserByEmail(String email);

}
