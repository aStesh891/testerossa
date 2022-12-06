package ua.testerossa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testerossa.model.dto.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

}
