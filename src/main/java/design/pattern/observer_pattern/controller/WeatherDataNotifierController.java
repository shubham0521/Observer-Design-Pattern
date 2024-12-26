package design.pattern.observer_pattern.controller;

import design.pattern.observer_pattern.service.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather-data-notifier")
public class WeatherDataNotifierController {

  @Autowired
  private Publisher publisher;

  @PostMapping("/notify")
  public void speak(@RequestParam(value = "temperature") float temperature,
      @RequestParam(value = "pressure") float pressure,
      @RequestParam(value = "humidity") float humidity) {
    publisher.setMeasurement(temperature, humidity, pressure);
  }

  @PutMapping("/remove-subscriber")
  public void removeSubscriber(@RequestParam(value = "subscriber") String subscriber) {
    publisher.removeSubscriber(subscriber);
  }
}
