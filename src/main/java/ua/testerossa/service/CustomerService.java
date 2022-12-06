package ua.testerossa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testerossa.model.dto.Customer;
import ua.testerossa.repository.CustomersRepository;

import java.util.List;

@Slf4j
@Service
public class CustomerService {
  
  private final CustomersRepository customersRepository;
  
  @Autowired
  public CustomerService(CustomersRepository customersRepository) {
    this.customersRepository = customersRepository;
  }

  public Customer findById(Long id) {
    return customersRepository.findById(id).orElse(null);
  }
  
  public List<Customer> findAll() {
    return customersRepository.findAll();
  }
  
  public Customer saveCustomer(Customer customer) {
    log.info("saveCustomer: {}", customer.toString());
    return customersRepository.
        save(customer);
  }
  
  public void deleteById(Long id) {
    log.info("deleteById: {}", id);
    customersRepository.deleteById(id);
  }
}
