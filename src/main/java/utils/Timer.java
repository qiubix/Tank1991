package utils;

public class Timer {

  private long previousTickTime;

  public Timer() {
    previousTickTime = getCurrentTime();
  }

  private long getCurrentTime() {
    return System.currentTimeMillis();
  }

  public long tick() {
    long elapsedTime = getCurrentTime() - previousTickTime;
    previousTickTime += elapsedTime;
    return elapsedTime;
  }
}
