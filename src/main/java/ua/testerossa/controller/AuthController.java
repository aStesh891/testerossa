package ua.testerossa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testerossa.model.Role;
import ua.testerossa.model.Status;
import ua.testerossa.model.db.User;
import ua.testerossa.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;
  
  public AuthController(UserService userService) {
    this.userService = userService;
  }
  
  @GetMapping("/login")
  public String getLoginPage() {
    return "login";
  }

  @GetMapping("/success")
  public String getSuccessPage() {
    return "success";
  }

  @GetMapping("/error")
  public String getErrorPage() {
    return "error";
  }

  @GetMapping("/registration")
  public String getRegistrationPage() {
    return "registration";
  }

  @PostMapping("/send-registration")
  public String getSendRegistrationPage(HttpServletRequest request) {
    String fullName = request.getParameter("fullname");
    String userName = request.getParameter("username");
    String password = request.getParameter("password");
    User user = new User();
    user.setEmail(userName);
    user.setFullName(fullName);
    user.setPassword(password); //todo add encode
    user.setRole(Role.DEFAULT);
    user.setStatus(Status.ADDED);

    userService.saveUser(user);
    return "success_reg";
  }
}
