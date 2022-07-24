package ua.testerossa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tester {
  private Long id;
  private String firstName;
  private String lastName;
}