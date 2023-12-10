package kr.intraview.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.intraview.model.UserDTO;
import kr.intraview.model.UserDetailsImpl;

@Controller
@RequestMapping("/interviews")
public class InterviewController {

  @GetMapping("")
  public String interviews(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    return userDetails.getUsername();
  }

}
