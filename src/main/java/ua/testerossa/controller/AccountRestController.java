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
import ua.testerossa.model.dto.Account;
import ua.testerossa.service.AccountService;
import ua.testerossa.service.CustomerService;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class AccountRestController {

  private final AccountService accountService;

  private final CustomerService customerService;

  @Autowired
  public AccountRestController(AccountService accountService, CustomerService customerService) {
    this.accountService = accountService;
    this.customerService = customerService;
  }

  @GetMapping("/accounts")
  @PreAuthorize("hasAuthority('read')")
  public String findAll(Model model) {
    List<Account> accounts = accountService.findAll();
    model.addAttribute("accounts", accounts);
    return "account-list";
  }

  @GetMapping("/account-create")
  @PreAuthorize("hasAuthority('read')")
  public String createAccountForm(Model model) {
    model.addAttribute("account", new Account());
    model.addAttribute("timer", new Timer());
    return "account-create";
  }

  @PostMapping("/account-create")
  @PreAuthorize("hasAuthority('read')")
  public String createAccount(Account account, Timer timer) throws Exception {
    log.info("createAccount:account={}, timer={}", account.toString(), timer.toString());

    //delay
    if (timer.getDelayTime() != 0) {
      CompletableFuture.runAsync(() -> {
        try {
          log.info("createAccount delay: {}", timer.getDelayTime());
          TimeUnit.MILLISECONDS.sleep(timer.getDelayTime());

          if (Objects.nonNull(account.getCustomer()) && Objects.isNull(customerService.findById(account.getCustomer().getCustomerId()))) {
            log.info("saveCustomer:saveCustomer={}, timer={}", account.getCustomer().toString(), timer.toString());
            customerService.saveCustomer(account.getCustomer());
          }
          accountService.saveAccount(account);
        } catch (InterruptedException ex) {
          log.error("Exception on createAccount: {}" + ex.getMessage());
        }
      });
    } else {
      if (Objects.nonNull(account.getCustomer()) && Objects.isNull(customerService.findById(account.getCustomer().getCustomerId()))) {
        log.info("saveCustomer:saveCustomer={}", account.getCustomer().toString());
        customerService.saveCustomer(account.getCustomer());
      }
      accountService.saveAccount(account);
    }

    return "redirect:/accounts";
  }

  @GetMapping("/account-update/{id}")
  @PreAuthorize("hasAuthority('read')")
  public String updateAccountForm(@PathVariable("id") Long id, Model model) {
    Account account = accountService.findById(id);
    model.addAttribute("account", account);
    model.addAttribute("timer", new Timer());
    return "account-update";
  }

  @PostMapping("/account-update")
  @PreAuthorize("hasAuthority('read')")
  public String updateAccount(Account account, Timer timer) {
    log.info("updateAccount:account={}, timer={}", account.toString(), timer.toString());
    //delay
    if (timer.getDelayTime() != 0) {
      CompletableFuture.runAsync(() -> {
        try {
          log.info("updateAccount delay: {}", timer.getDelayTime());
          TimeUnit.MILLISECONDS.sleep(timer.getDelayTime());
          if (Objects.nonNull(account.getCustomer()) && Objects.isNull(customerService.findById(account.getCustomer().getCustomerId()))) {
            log.info("saveCustomer:saveCustomer={}", account.getCustomer().toString());
            customerService.saveCustomer(account.getCustomer());
          }
          accountService.saveAccount(account);
        } catch (InterruptedException ex) {
          log.error("Exception on updateAccount: {}" + ex.getMessage());
        }
      });
    } else {
      if (Objects.nonNull(account.getCustomer()) && Objects.isNull(customerService.findById(account.getCustomer().getCustomerId()))) {
        log.info("saveCustomer:saveCustomer={}", account.getCustomer().toString());
        customerService.saveCustomer(account.getCustomer());
      }
      accountService.saveAccount(account);
    }

    return "redirect:/accounts";
  }
  
  @GetMapping("account-delete/{id}")
  @PreAuthorize("hasAuthority('read')")
  public String deleteAccount(@PathVariable("id") Long id) {
    accountService.deleteById(id);
    return "redirect:/accounts";
  }
}
