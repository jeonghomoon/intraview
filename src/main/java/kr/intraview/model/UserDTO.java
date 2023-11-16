package kr.intraview.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {

  private String email;
  private String password;

  @Builder
  public UserDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }

}
