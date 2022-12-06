package ua.testerossa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testerossa.model.dto.User;
import ua.testerossa.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
public class UserService {

  private final UserRepository usersRepository;

  @Autowired
  public UserService(UserRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public User findById(Long id) {
    return usersRepository.findById(id).orElse(null);
  }
  
  public List<User> findAll() {
    return usersRepository.findAll();
  }
  
  public User saveUser(User user) {
    log.info("saveUser user:{}", user.toString());
    return usersRepository.
        save(user);
  }

  public void deleteById(Long id) {
    usersRepository.deleteById(id);
  }
}
