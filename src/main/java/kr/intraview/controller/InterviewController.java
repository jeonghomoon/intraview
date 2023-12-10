package kr.intraview.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.intraview.model.Interview;
import kr.intraview.model.InterviewDetail;
import kr.intraview.model.UserDetailsImpl;
import kr.intraview.service.GptApiService;
import kr.intraview.service.InterviewService;

@Controller
@RequestMapping("/interviews")
public class InterviewController {

  private final GptApiService gptApiService;
  private final InterviewService interviewService;

  public InterviewController(
    GptApiService gptApiService,
    InterviewService interviewService
  ) {
    this.gptApiService = gptApiService;
    this.interviewService = interviewService;
  }

  @GetMapping("")
  public String showInterviewsPage(
    @AuthenticationPrincipal UserDetailsImpl userDetails,
    Model model
  ) {
    List<Interview> interviews = interviewService.loadInterviewsByUserId(userDetails.getId());
    model.addAttribute("interviews", interviews);
    return "interviews";
  }

  @GetMapping("/prepare")
  public String showPreparePage() {
    return "prepare";
  }

  @PostMapping("/prepare")
  public String prepareInterview(
    @AuthenticationPrincipal UserDetailsImpl userDetails,
    String title,
    String resume
  ) throws Exception {
    String interviewId = interviewService.createInterview(userDetails.getId(), title, resume);
    gptApiService.generateQuestions(interviewId, resume);
    return "redirect:/interviews/" + interviewId;
  }

  @GetMapping("/{interviewId}")
  public String showInterviewPage(
    @PathVariable String interviewId,
    Model model
  ) {
    InterviewDetail interviewDetail = interviewService
      .loadInterviewDetailById(interviewId);
    model.addAttribute("interviewDetail", interviewDetail);
    return "interview";
  }

  @PostMapping("/{interviewId}/questions/{questionId}/answer")
  public String answerQuestion(
    @PathVariable String interviewId,
    @PathVariable String questionId,
    String content
  ) throws Exception {
    gptApiService.generateFeedbackAndFollowup(questionId, content);
    return "redirect:/interviews/" + interviewId; 
  }

}
