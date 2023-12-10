package kr.intraview.model;

public class Answer {

  private String id;
  private String questionId;
  private String content;
  private String feedback;
  private String followup;

  public Answer() {
  }

  public Answer(
    String id,
    String questionId,
    String content,
    String feedback,
    String followup
  ) {
    this.id = id;
    this.questionId = questionId;
    this.content = content;
    this.feedback = feedback;
    this.followup = followup;
  }

  public String getId() {
    return id;
  }

  public String getQuestionId() {
    return questionId;
  }

  public String getContent() {
    return content;
  }

  public String getFeedback() {
    return feedback;
  }

  public String getFollowup() {
    return followup;
  }

}
