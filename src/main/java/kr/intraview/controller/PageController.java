package kr.intraview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.intraview.model.UserDTO;

@Controller
public class PageController {

  @GetMapping("/register")
  public String showRegisterPage(Model model) {
    if (!model.containsAttribute("user")) {
      model.addAttribute("user", UserDTO.builder().build());
    }
    return "register";
  }

  @GetMapping("/login")
  public String showLoginPage(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      if (session.getAttribute("loginErrorMessage") != null) {
        model.addAttribute("loginErrorMessage", session.getAttribute("loginErrorMessage"));
        session.removeAttribute("loginErrorMessage");
      }
      if (session.getAttribute("loginEmail") != null) {
        model.addAttribute("loginEmail", session.getAttribute("loginEmail"));
        session.removeAttribute("loginEmail");
      }
    }
    return "login";
  }

}
