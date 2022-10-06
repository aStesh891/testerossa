package ua.testerossa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timer {

  private Integer delay = null;
  private TimeUnit timeUnit;

  @DateTimeFormat(pattern = "HH:mm:ss")
  private LocalTime time;

  public int getDelayTime() {
    if (delay != null) {
      switch (timeUnit) {
        case MILLISECOND:
          return delay;
        case SECOND:
          return delay * 1000;
        case MINUTE:
          return delay * 60 * 1000;
        case HOUR:
          return delay * 60 * 60 * 1000;
        case DAY:
          return delay * 24 * 60 * 60 * 1000;
        case WEEK:
          return delay * 7 * 24 * 60 * 60 * 1000;
        case MONTH:
          return delay * 4 * 7 * 24 * 60 * 60 * 1000;
        case YEAR:
          return delay * 12* 4 * 7 * 24 * 60 * 60 * 1000;
      }
    }

    if (time != null) {
      return (int) Duration.between(LocalTime.now(), time).toMillis();
    }

    return 0;
  }
}