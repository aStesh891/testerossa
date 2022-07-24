package ua.testerossa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testerossa.model.db.Customer;
import ua.testerossa.repository.CustomersRepository;

import java.util.List;

@Service
public class CustomerService {
  
  private final CustomersRepository customersRepository;
  
  @Autowired
  public CustomerService(CustomersRepository customersRepository) {
    this.customersRepository = customersRepository;
  }
  
  //todo - add validation in methods
  public Customer findById(Long id) {
    return customersRepository.findById(id).orElse(null);
  }
  
  public List<Customer> findAll() {
    return customersRepository.findAll();
  }
  
  public Customer saveCustomer(Customer customer) {
    return customersRepository.
        save(customer);
  }
  
  public void deleteById(Long id) {
    customersRepository.deleteById(id);
  }
}
