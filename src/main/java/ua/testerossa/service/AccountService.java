package ua.testerossa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testerossa.model.dto.Account;
import ua.testerossa.repository.AccountsRepository;

import java.util.List;

@Slf4j
@Service
public class AccountService {

  private final AccountsRepository accountsRepository;

  @Autowired
  public AccountService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public Account findById(Long id) {
    return accountsRepository.findById(id).orElse(null);
  }
  
  public List<Account> findAll() {
    return accountsRepository.findAll();
  }
  
  public Account saveAccount(Account account) {
    log.info("saveAccount: {}", account.toString());
    return accountsRepository.save(account);
  }
  
  public void deleteById(Long id) {
    log.info("deleteById: {}", id);
    accountsRepository.deleteById(id);
  }
}
