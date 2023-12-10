package kr.intraview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

  @PostMapping("/register")
  public String registerUser(
    @ModelAttribute("user") UserDTO userDto,
    RedirectAttributes redirectAttributes
  ) {
    try {
      userService.createUser(userDto);
      return "redirect:/login";
    } catch (DuplicateEmailException e) {
      UserDTO redirectUserDto = UserDTO.builder()
        .email(userDto.getEmail())
        .build();

      redirectAttributes.addFlashAttribute("user", redirectUserDto);
      redirectAttributes.addFlashAttribute("error", "이미 사용 중인 이메일입니다.");
      return "redirect:/register";
    }
  }

}
