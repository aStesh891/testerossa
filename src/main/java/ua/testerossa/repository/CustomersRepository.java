package ua.testerossa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testerossa.model.db.Customer;

public interface CustomersRepository extends JpaRepository<Customer, Long> {

}
