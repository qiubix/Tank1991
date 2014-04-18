package utils;

import java.util.LinkedList;
import java.util.List;

public abstract class Observed {

  private List<Observer> observers = new LinkedList<Observer>();

  public boolean addObserver(Observer observer) {
    return observers.add(observer);
  }

  public boolean removeObserver(Observer observer) {
    return observers.remove(observer);
  }

  public void clearObserversList() {
    observers.clear();
  }

  public void signalToObservers() {
    for (Observer o : observers) {
      o.signal(this);
    }
  }
}
