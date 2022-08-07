package ua.testerossa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

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
  public String getSendRegistrationPage() {
    //todo - send message
    return "success_reg";
  }
}
