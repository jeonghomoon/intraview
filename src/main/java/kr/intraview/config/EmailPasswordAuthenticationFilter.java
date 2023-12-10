package kr.intraview.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmailPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  public EmailPasswordAuthenticationFilter() {
    super();
    setUsernameParameter("email");
    setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/users/login", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws AuthenticationException {
    request.getParameterNames().asIterator().forEachRemaining(name -> System.out.println(name + ": " + request.getParameter(name))); 
    String email = obtainUsername(request);
    String password = obtainPassword(request);

    if (email == null) {
        email = "";
    }

    if (password == null) {
        password = "";
    }

    email = email.trim();

    UsernamePasswordAuthenticationToken authRequest =
      new UsernamePasswordAuthenticationToken(email, password);

    setDetails(request, authRequest);

    return this.getAuthenticationManager().authenticate(authRequest);
  }

}
