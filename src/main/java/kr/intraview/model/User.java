package kr.intraview.model;

public class User {

  private String id;
  private String email;
  private String password;

  public User(String id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public String getId() {
    return this.id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

}
