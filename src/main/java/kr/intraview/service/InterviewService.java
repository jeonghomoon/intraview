package kr.intraview.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.intraview.mapper.InterviewMapper;
import kr.intraview.model.Interview;
import kr.intraview.model.InterviewDetail;

@Service
public class InterviewService {

  private final InterviewMapper interviewMapper;

  public InterviewService(InterviewMapper interviewMapper) {
    this.interviewMapper = interviewMapper;
  }

  public String createInterview(String id, String title, String resume) {
    Interview interview = new Interview(UUID.randomUUID().toString(), id, title, resume);
    interviewMapper.insertInterview(interview);
    return interview.getId();
  }

  public List<Interview> loadInterviewsByUserId(String id) {
    return interviewMapper.findInterviewsByUserId(id);
  }

  public InterviewDetail loadInterviewDetailById(String interviewId) {
    return interviewMapper.findInterviewDetailById(interviewId);
  }

}
