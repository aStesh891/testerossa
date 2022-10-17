package ua.testerossa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testerossa.model.db.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

}
