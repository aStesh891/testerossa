package ua.testerossa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
class SpringSecurityApplicationTests {

  @Test
  void contextLoads() {
    System.out.println("LocalTime.now():" + LocalTime.now());
  }

}
