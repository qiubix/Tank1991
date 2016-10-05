package utils;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TimerTest {

  @Test
  public void shouldMeasureElapsedTimeSinceLastTick() {
    try {
      Timer timer = new Timer();
      timer.tick();
      final long sleepTime = 30;

      Thread.sleep(sleepTime);

      long elapsedTime = timer.tick();

      assertThat(elapsedTime, equalTo(sleepTime));

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
