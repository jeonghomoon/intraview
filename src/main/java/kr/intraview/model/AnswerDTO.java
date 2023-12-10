package kr.intraview.model;

public class AnswerDTO {

  private String questionId;
  private String content;
  private String feedback;
  private String followup;

  public AnswerDTO(
    String questionId,
    String content,
    String feedback,
    String followup
  ) {
    this.questionId = questionId;
    this.content = content;
    this.feedback = feedback;
    this.followup = followup;
  }

  public String getQuestionId() {
    return this.questionId;
  }

  public String getContent() {
    return this.content;
  }

  public String getFeedback() {
    return this.feedback;
  }

  public String getFollowup() {
    return this.followup;
  }

}
