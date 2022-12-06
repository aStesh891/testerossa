package ua.testerossa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testerossa.model.dto.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {

}
