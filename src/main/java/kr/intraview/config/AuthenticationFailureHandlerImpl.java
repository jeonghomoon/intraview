package kr.intraview.config;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException exception
  ) throws IOException, ServletException {
    String errorMessage;
    if (exception instanceof UsernameNotFoundException) {
      errorMessage = "계정을 찾을 수 없습니다.";
    } else if (exception instanceof BadCredentialsException) {
      errorMessage = "로그인에 실패하였습니다.";
    } else {
      errorMessage = "알 수 없는 오류입니다.";
    }
 
    String email = request.getParameter("email"); 

    HttpSession session = request.getSession();
    session.setAttribute("loginEmail", email);
    session.setAttribute("loginErrorMessage", errorMessage);

    response.sendRedirect("/login");
  }

}
