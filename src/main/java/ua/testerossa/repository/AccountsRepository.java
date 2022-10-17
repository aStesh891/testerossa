package ua.testerossa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testerossa.model.db.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {

}
