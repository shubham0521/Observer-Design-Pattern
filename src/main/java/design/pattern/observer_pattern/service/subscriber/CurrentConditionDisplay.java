package design.pattern.observer_pattern.service.subscriber;

import design.pattern.observer_pattern.service.WeatherData;
import org.springframework.stereotype.Component;

@Component
public class CurrentConditionDisplay implements DisplayElement, Subscriber{
  public WeatherData weatherData;
  private float temperature;
  private float humidity;
  private float pressure;

  CurrentConditionDisplay(WeatherData weatherData) {
    this.weatherData = weatherData;
    weatherData.addSubscriber(this);
  }

  @Override
  public void display() {
    System.out.println("Current Condition Display");
    System.out.println("temperature: " + temperature);
    System.out.println("humidity: " + humidity);
    System.out.println("pressure: " + pressure);
  }

  @Override
  public void update() {
    this.temperature = weatherData.getTemperature();
    this.humidity = weatherData.getHumidity();
    this.pressure = weatherData.getPressure();
    display();
  }
}
