package design.pattern.observer_pattern.service;

import design.pattern.observer_pattern.service.subscriber.CurrentConditionDisplay;
import design.pattern.observer_pattern.service.subscriber.ForecastDisplay;
import design.pattern.observer_pattern.service.subscriber.StatisticsDisplay;
import design.pattern.observer_pattern.service.subscriber.Subscriber;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@ConditionalOnClass({CurrentConditionDisplay.class, StatisticsDisplay.class, ForecastDisplay.class})
public class WeatherData implements Publisher {

  private static final Map<String, String> CLASS_NAME_TO_FULL_NAME = Map.of(
      "CurrentConditionDisplay", "design.pattern.observer_pattern.service.subscriber.CurrentConditionDisplay",
      "ForecastDisplay", "design.pattern.observer_pattern.service.subscriber.ForecastDisplay",
      "StatisticsDisplay", "design.pattern.observer_pattern.service.subscriber.StatisticsDisplay"
  );

  List<Subscriber> subscribers = new ArrayList<>();
  private float temperature;
  private float humidity;
  private float pressure;


  @Override
  public void addSubscriber(Subscriber subscriber) {
    subscribers.add(subscriber);
  }




  @Override
  public void removeSubscriber(String subscriber) {
    List<Subscriber> toRemove = subscribers.stream()
        .filter(value -> subscriber.equals(value.getClass().getSimpleName()))
        .toList();

    subscribers.removeAll(toRemove);
  }

  @Override
  public void notifySubscriber() {
    System.out.println(subscribers.toString());
    subscribers.stream().forEach(subscriber -> {
      subscriber.update();
    });
  }

  @Override
  public void setMeasurement(float temperature, float humidity, float pressure) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    notifySubscriber();
  }

  public float getTemperature() {
    return temperature;
  }

  public float getHumidity() {
    return humidity;
  }

  public float getPressure() {
    return pressure;
  }
}
