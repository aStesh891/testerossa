package ua.testerossa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testerossa.model.Role;
import ua.testerossa.model.Status;
import ua.testerossa.model.db.User;
import ua.testerossa.service.UserService;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

  private final static BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();

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
  public String getRegistrationPage(Model model) {
    model.addAttribute("user", new User());
    return "registration";
  }

  @PostMapping("/send-registration")
  public String getSendRegistrationPage(User user) {
    log.info("getSendRegistrationPage user:{}", user);
    user.setPassword(B_CRYPT_PASSWORD_ENCODER.encode(user.getPassword()));
    user.setRole(Role.DEFAULT);
    user.setStatus(Status.ADDED);
    userService.saveUser(user);
    return "success_reg";
  }
}
