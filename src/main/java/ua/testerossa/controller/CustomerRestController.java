package ua.testerossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.testerossa.model.db.Customer;
import ua.testerossa.service.CustomerService;

import java.util.List;

@Controller
public class CustomerRestController {
  
  private final CustomerService customerService;
  
  @Autowired
  public CustomerRestController(CustomerService customerService) {
    this.customerService = customerService;
  }
  
  @GetMapping("/customers")
  public String findAll(Model model) {
    List<Customer> customers = customerService.findAll();
    model.addAttribute("customers", customers);
    return "customer-list";
  }
  
  @GetMapping("/customer-create")
  public String createUserForm(Customer customer) {
    return "customer-create";
  }
  
  @PostMapping("/customer-create")
  public String createCustomer(Customer customer) {
    customerService.saveCustomer(customer);
    return "redirect:/customers";
  }
  
  @GetMapping("/customer-update/{id}")
  public String updateCustomerForm(@PathVariable("id") Long id, Model model) {
    Customer customer = customerService.findById(id);
    model.addAttribute("customer", customer);
    return "customer-update";
  }
  
  @PostMapping("/customer-update")
  public String updateCustomer(Customer customer) {
    customerService.saveCustomer(customer);
    return "redirect:/customers";
  }
  
  @GetMapping("customer-delete/{id}")
  public String deleteUser(@PathVariable("id") Long id) {
    customerService.deleteById(id);
    return "redirect:/customers";
  }
}