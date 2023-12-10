package kr.intraview.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.intraview.model.Answer;

@Mapper
public interface AnswerMapper {

  void insertAnswer(Answer answer);

}
