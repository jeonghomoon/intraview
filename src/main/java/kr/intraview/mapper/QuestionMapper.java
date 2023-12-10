package kr.intraview.mapper;
import org.apache.ibatis.annotations.Mapper;

import kr.intraview.model.Question;

@Mapper
public interface QuestionMapper {

  void insertQuestion(Question question);

  Question findById(String questionId);

  Question[] findQuestionsByInterviewId(String interviewId);

}
