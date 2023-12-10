package kr.intraview.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.intraview.model.Interview;
import kr.intraview.model.InterviewDetail;

@Mapper
public interface InterviewMapper {

  void insertInterview(Interview interview);

  List<Interview> findInterviewsByUserId(String userId);

  InterviewDetail findInterviewDetailById(String interviewId);

}
