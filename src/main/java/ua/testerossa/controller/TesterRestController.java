package ua.testerossa.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.testerossa.model.Tester;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class TesterRestController {

  private List<Tester> TESTERS = Stream.of(
      new Tester(1L, "Taras", "Shevchenko"),
      new Tester(2L, "Lesya", "Ukrainka"),
      new Tester(3L, "Ivan", "Franko")
  ).collect(Collectors.toList());


  @GetMapping
  public List<Tester> getAll() {
    return TESTERS;
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('read')")
  public Tester getById(@PathVariable Long id) {
    return TESTERS.stream()
        .filter(tester -> tester.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  @PostMapping
  @PreAuthorize("hasAuthority('create')")
  public Tester createDeveloper(@RequestBody Tester tester) {
    this.TESTERS.add(tester);
    return tester;
  }

  @DeleteMapping
  @PreAuthorize("hasAuthority('delete')")
  public void deleteDeveloper(@PathVariable Long id) {
    this.TESTERS.removeIf(tester -> tester.getId().equals(id));
  }
}
