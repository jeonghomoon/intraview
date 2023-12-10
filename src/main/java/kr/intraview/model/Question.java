package kr.intraview.model;

public class Question {

  private String id;
  private String interviewId;
  private String content;

  public Question() {
  }

  public Question(String id, String interviewId, String content) {
    this.id = id;
    this.interviewId = interviewId;
    this.content = content;
  }

  public String getId() {
    return this.id;
  }

  public String getInterviewId() {
    return this.interviewId;
  }

  public String getContent() {
    return this.content;
  }

}
