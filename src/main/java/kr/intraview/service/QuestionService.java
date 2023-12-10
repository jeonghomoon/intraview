package kr.intraview.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.intraview.mapper.QuestionMapper;
import kr.intraview.model.Question;

@Service
public class QuestionService {

  private final QuestionMapper questionMapper;

  public QuestionService(QuestionMapper questionMapper) {
    this.questionMapper = questionMapper;
  }

  public void createQuestion(String interviewId, String content) {
    Question question = new Question(
      UUID.randomUUID().toString(),
      interviewId,
      content
    );
    questionMapper.insertQuestion(question);
  }

  public Question loadQuestionById(String questionId) {
    return questionMapper.findById(questionId);
  }

}
