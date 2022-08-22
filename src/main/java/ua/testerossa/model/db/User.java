package ua.testerossa.model.db;

import lombok.Data;
import ua.testerossa.model.Role;
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
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;
  @Column(name = "full_name")
  private String fullName;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "role")
  private Role role;
  @Enumerated(value = EnumType.STRING)
  @Column(name = "status")
  private Status status;

}
