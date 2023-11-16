package kr.intraview.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

  private String id;
  private String email;
  private String password;

  @Builder
  public User(String id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

}
