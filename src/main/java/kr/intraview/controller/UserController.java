package kr.intraview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.intraview.exception.DuplicateEmailException;
import kr.intraview.model.UserDTO;
import kr.intraview.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String showRegisterPage(Model model) {
    model.addAttribute("user", UserDTO.builder().build());
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(
    @ModelAttribute("user") UserDTO userDto,
    BindingResult result
  ) {
    try {
      userService.createUser(userDto);
      return "redirect:/login";
    } catch (DuplicateEmailException e) {
      result.rejectValue("email", "error.user", "이미 사용 중인 이메일입니다.");
      return "register";
    }
  }

}
