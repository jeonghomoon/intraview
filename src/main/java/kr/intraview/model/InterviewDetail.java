package kr.intraview.model;

import java.util.List;

public class InterviewDetail extends Interview {
  
  private List<QuestionDetail> questions;

  public InterviewDetail() {
  }

  public InterviewDetail(List<QuestionDetail> questions) {
    this.questions = questions;
  }

  public List<QuestionDetail> getQuestions() {
    return questions;
  }

}
