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
import ua.testerossa.model.db.Customer;
import ua.testerossa.service.CustomerService;
import ua.testerossa.utils.SecurityUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class CustomerRestController {

  private final CustomerService customerService;

  @Autowired
  public CustomerRestController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/customers")
  @PreAuthorize("hasAuthority('read')")
  public String findAll(Model model) {
    List<Customer> customers = customerService.findAll();
    model.addAttribute("customers", customers);
    return "customer-list";
  }

  @GetMapping("/customer-create")
  @PreAuthorize("hasAuthority('read')")
  public String createCustomerForm(Model model) {
    model.addAttribute("customer", new Customer());
    model.addAttribute("timer", new Timer());
    return "customer-create";
  }

  @PostMapping("/customer-create")
  @PreAuthorize("hasAuthority('read')")
  public String createCustomer(Customer customer, Timer timer) {
    log.info("createCustomer:customer={}, timer={}", customer.toString(), timer.toString());
    customer.setPassword(SecurityUtils.aes(customer.getPassword(), SecurityUtils.SECRET_KEY_256));

    //delay
    if (timer.getDelayTime() != 0) {
      CompletableFuture.runAsync(() -> {
        try {
          log.info("createCustomer delay: {}", timer.getDelayTime());
          TimeUnit.MILLISECONDS.sleep(timer.getDelayTime());
          customerService.saveCustomer(customer);
        } catch (InterruptedException ex) {
          log.error("Exception on createCustomer: {}" + ex.getMessage());
        }
      });
    } else {
      customerService.saveCustomer(customer);
    }

    return "redirect:/customers";
  }

  @GetMapping("/customer-update/{id}")
  @PreAuthorize("hasAuthority('read')")
  public String updateCustomerForm(@PathVariable("id") Long id, Model model) {
    Customer customer = customerService.findById(id);
    customer.setPassword(SecurityUtils.decrypt(customer.getPassword(), SecurityUtils.SECRET_KEY_256));

    model.addAttribute("customer", customer);
    model.addAttribute("timer", new Timer());
    return "customer-update";
  }

  @PostMapping("/customer-update")
  @PreAuthorize("hasAuthority('read')")
  public String updateCustomer(Customer customer, Timer timer) {
    log.info("updateCustomer:customer={}, timer={}", customer.toString(), timer.toString());

    //encode
    customer.setPassword(SecurityUtils.aes(customer.getPassword(), SecurityUtils.SECRET_KEY_256));

    //delay update
    if (timer.getDelayTime() != 0) {
      CompletableFuture.runAsync(() -> {
        try {
          log.info("updateCustomer delay: {}", timer.getDelayTime());
          TimeUnit.MILLISECONDS.sleep(timer.getDelayTime());
          customerService.saveCustomer(customer);
        } catch (InterruptedException ex) {
          log.error("Exception on updateCustomer: {}" + ex.getMessage());
        }
      });
    } else {
      customerService.saveCustomer(customer);
    }

    return "redirect:/customers";
  }
  
  @GetMapping("customer-delete/{id}")
  @PreAuthorize("hasAuthority('read')")
  public String deleteCustomer(@PathVariable("id") Long id) {
    customerService.deleteById(id);
    return "redirect:/customers";
  }
}
