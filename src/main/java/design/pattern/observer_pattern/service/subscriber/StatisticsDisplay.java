package design.pattern.observer_pattern.service.subscriber;

import design.pattern.observer_pattern.service.WeatherData;
import org.springframework.stereotype.Component;

@Component
public class StatisticsDisplay implements Subscriber, DisplayElement {

  public WeatherData weatherData;
  private float temperature;
  private float humidity;
  private float pressure;

  StatisticsDisplay(WeatherData weatherData) {
    this.weatherData = weatherData;
    weatherData.addSubscriber(this);
  }

  @Override
  public void display() {
    System.out.println("Statistics Display");
    System.out.println("temperature: " + temperature);
    System.out.println("pressure: " + pressure);
    System.out.println("humidity: " + humidity);
  }

  @Override
  public void update() {
    this.temperature = weatherData.getTemperature();
    this.humidity = weatherData.getHumidity();
    this.pressure = weatherData.getPressure();
    display();
  }
}
