package kr.intraview.model;

import java.util.List;

public class QuestionDetail extends Question {
  
  private List<Answer> answers;

  public QuestionDetail() {
  }

  public QuestionDetail(List<Answer> answers) {
    this.answers = answers;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

}
