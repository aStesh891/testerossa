package ua.testerossa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testerossa.model.db.User;
import ua.testerossa.repository.UserRepository;

import java.util.List;

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
    return usersRepository.
        save(user);
  }
  
  public void deleteById(Long id) {
    usersRepository.deleteById(id);
  }
}
