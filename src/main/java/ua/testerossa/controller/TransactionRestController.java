package ua.testerossa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.testerossa.model.Timer;
import ua.testerossa.model.db.Transaction;
import ua.testerossa.service.TransactionService;
import ua.testerossa.service.AccountService;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class TransactionRestController {

  private final TransactionService transactionService;

  private final AccountService accountService;

  @Autowired
  public TransactionRestController(TransactionService transactionService, AccountService accountService) {
    this.transactionService = transactionService;
    this.accountService = accountService;
  }

  @GetMapping("/transactions")
  @PreAuthorize("hasAuthority('read')")
  public String findAll(Model model) {
    List<Transaction> transactions = transactionService.findAll();
    model.addAttribute("transactions", transactions);
    return "transaction-list";
  }

  @GetMapping("/transaction-create")
  @PreAuthorize("hasAuthority('read')")
  public String createTransactionForm(Model model) {
    model.addAttribute("transaction", new Transaction());
    model.addAttribute("timer", new Timer());
    return "transaction-create";
  }

  @PostMapping("/transaction-create")
  @PreAuthorize("hasAuthority('read')")
  public String createTransaction(Transaction transaction, Timer timer) throws Exception {
    log.info("createTransaction:transaction={}, timer={}", transaction.toString(), timer.toString());

    //delay
    if (timer.getDelayTime() != 0) {
      CompletableFuture.runAsync(() -> {
        try {
          log.info("createTransaction delay: {}", timer.getDelayTime());
          TimeUnit.MILLISECONDS.sleep(timer.getDelayTime());

          if (Objects.nonNull(transaction.getAccount()) && Objects.isNull(accountService.findById(transaction.getAccount().getAccountId()))) {
            log.info("saveAccount:saveAccount={}, timer={}", transaction.getAccount().toString(), timer.toString());
            accountService.saveAccount(transaction.getAccount());
          }
          transactionService.saveTransaction(transaction);
        } catch (InterruptedException ex) {
          log.error("Exception on createTransaction: {}" + ex.getMessage());
        }
      });
    } else {
      if (Objects.nonNull(transaction.getAccount()) && Objects.isNull(accountService.findById(transaction.getAccount().getAccountId()))) {
        log.info("saveAccount:saveAccount={}", transaction.getAccount().toString());
        accountService.saveAccount(transaction.getAccount());
      }
      transactionService.saveTransaction(transaction);
    }

    return "redirect:/transactions";
  }

  @GetMapping("/transaction-update/{id}")
  @PreAuthorize("hasAuthority('read')")
  public String updateTransactionForm(@PathVariable("id") Long id, Model model) {
    Transaction transaction = transactionService.findById(id);
    model.addAttribute("transaction", transaction);
    model.addAttribute("timer", new Timer());
    return "transaction-update";
  }

  @PostMapping("/transaction-update")
  @PreAuthorize("hasAuthority('read')")
  public String updateTransaction(Transaction transaction, Timer timer) {
    log.info("updateTransaction:transaction={}, timer={}", transaction.toString(), timer.toString());
    //delay
    if (timer.getDelayTime() != 0) {
      CompletableFuture.runAsync(() -> {
        try {
          log.info("updateTransaction delay: {}", timer.getDelayTime());
          TimeUnit.MILLISECONDS.sleep(timer.getDelayTime());
          if (Objects.nonNull(transaction.getAccount()) && Objects.isNull(accountService.findById(transaction.getAccount().getAccountId()))) {
            log.info("saveAccount:saveAccount={}", transaction.getAccount().toString());
            accountService.saveAccount(transaction.getAccount());
          }
          transactionService.saveTransaction(transaction);
        } catch (InterruptedException ex) {
          log.error("Exception on updateTransaction: {}" + ex.getMessage());
        }
      });
    } else {
      if (Objects.nonNull(transaction.getAccount()) && Objects.isNull(accountService.findById(transaction.getAccount().getAccountId()))) {
        log.info("saveAccount:saveAccount={}", transaction.getAccount().toString());
        accountService.saveAccount(transaction.getAccount());
      }
      transactionService.saveTransaction(transaction);
    }

    return "redirect:/transactions";
  }
  
  @GetMapping("transaction-delete/{id}")
  @PreAuthorize("hasAuthority('read')")
  public String deleteTransaction(@PathVariable("id") Long id) {
    transactionService.deleteById(id);
    return "redirect:/transactions";
  }
}
