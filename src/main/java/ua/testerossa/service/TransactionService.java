package ua.testerossa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testerossa.model.db.Transaction;
import ua.testerossa.repository.TransactionsRepository;
import ua.testerossa.repository.TransactionsRepository;

import java.util.List;

@Slf4j
@Service
public class TransactionService {
  
  private final TransactionsRepository transactionsRepository;
  
  @Autowired
  public TransactionService(TransactionsRepository transactionsRepository) {
    this.transactionsRepository = transactionsRepository;
  }

  public Transaction findById(Long id) {
    return transactionsRepository.findById(id).orElse(null);
  }
  
  public List<Transaction> findAll() {
    return transactionsRepository.findAll();
  }
  
  public Transaction saveTransaction(Transaction transaction) {
    log.info("saveTransaction: {}", transaction.toString());
    return transactionsRepository.
        save(transaction);
  }
  
  public void deleteById(Long id) {
    log.info("deleteById: {}", id);
    transactionsRepository.deleteById(id);
  }
}
