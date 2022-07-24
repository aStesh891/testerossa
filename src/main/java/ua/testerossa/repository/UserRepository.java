package ua.testerossa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testerossa.model.db.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
