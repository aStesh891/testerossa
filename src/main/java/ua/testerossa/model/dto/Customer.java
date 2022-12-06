package ua.testerossa.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.testerossa.model.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Long customerId;
  
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  
  @Column(name = "phone")
  private String phone;
  @Column(name = "password")
  private String password;
  @Column(name = "address")
  private String address;
  @Column(name = "dob")
  private String dob;
  
  @Column(name = "created_at")
  private String createdAt;
  @Column(name = "edited_at")
  private String editedAt;
  
  @Enumerated(value = EnumType.STRING)
  @Column(name = "status")
  private Status status;
}


