package kr.intraview.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.intraview.mapper.AnswerMapper;
import kr.intraview.model.Answer;
import kr.intraview.model.AnswerDTO;

@Service
public class AnswerService {

  private final AnswerMapper answerMapper;

  public AnswerService(AnswerMapper answerMapper) {
    this.answerMapper = answerMapper;
  }

  public void createAnswer(AnswerDTO answerDto) {
    Answer answer = new Answer(
      UUID.randomUUID().toString(),
      answerDto.getQuestionId(),
      answerDto.getContent(),
      answerDto.getFeedback(),
      answerDto.getFollowup()
    );
    answerMapper.insertAnswer(answer);
  }

}
