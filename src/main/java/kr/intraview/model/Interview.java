package kr.intraview.model;

public class Interview {

  private String id;
  private String userId;
  private String title;
  private String resume;

  public Interview() {
  }

  public Interview(String id, String userId, String title, String resume) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.resume = resume;
  }

  public String getId() {
    return this.id;
  }

  public String getUserId() {
    return this.userId;
  }

  public String getTitle() {
    return this.title;
  }

  public String getResume() {
    return this.resume;
  }

}
