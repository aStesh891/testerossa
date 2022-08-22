package ua.testerossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.testerossa.model.db.User;
import ua.testerossa.service.UserService;

import java.util.List;

@Controller
public class UsersRestController {

  private final UserService userService;

  @Autowired
  public UsersRestController(UserService userService) {
    this.userService = userService;
  }
  
  @GetMapping("/users")
  @PreAuthorize("hasAuthority('read')")
  public String findAll(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "users-list";
  }

  @GetMapping("/user-create")
  @PreAuthorize("hasAuthority('create')")
  public String createUserForm(User user) {
    return "user-create";
  }

  @PostMapping("/user-create")
  @PreAuthorize("hasAuthority('create')")
  public String createUser(User user) {
    userService.saveUser(user);
    return "redirect:/users";
  }

  @GetMapping("/user-update/{id}")
  @PreAuthorize("hasAuthority('update')")
  public String updateUserForm(@PathVariable("id") Long id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("user", user);
    return "user-update";
  }
  @PostMapping("/user-update")
  @PreAuthorize("hasAuthority('update')")
  public String updateUser(User user) {
    userService.saveUser(user);
    return "redirect:/users";
  }
  
  @GetMapping("user-delete/{id}")
  @PreAuthorize("hasAuthority('delete')")
  public String deleteUser(@PathVariable("id") Long id) {
    userService.deleteById(id);
    return "redirect:/users";
  }
}
