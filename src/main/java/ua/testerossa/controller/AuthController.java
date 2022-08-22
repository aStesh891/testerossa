package ua.testerossa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testerossa.service.EmailService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

  private final EmailService emailService;
  
  public AuthController(EmailService emailService) {
    this.emailService = emailService;
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
    
    String msg = "Full name:" + fullName + "\n" + "E-mail:" + userName + "\n" + "Password:" + password;
    emailService.sendEmail(userName, msg);
    return "success_reg";
  }
}
