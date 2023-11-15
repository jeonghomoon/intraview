package kr.intraview.service;

import kr.intraview.exception.DuplicateEmailException;
import kr.intraview.exception.EmailNotFoundException;
import kr.intraview.model.User;
import kr.intraview.model.UserDTO;

public interface UserService {

  public void createUser(UserDTO userDto) throws DuplicateEmailException;

  public User loadUserByEmail(String email) throws EmailNotFoundException;

}
