package design.pattern.observer_pattern.service;

import design.pattern.observer_pattern.service.subscriber.Subscriber;


public interface Publisher {

  void addSubscriber(Subscriber subscriber);

  void removeSubscriber(String subscriber);

  void notifySubscriber();

  void setMeasurement(float temperature, float humidity, float pressure);
}
