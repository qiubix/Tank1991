package core;

import objects.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Ignore
public class ControllerTest {

  private Model model;
  private Controller controller;
  private Robot robot;

  @Before
  public void setUp() {
    try {
      model = new Model();
      View view = new View(model);
      controller = new Controller(model, view);
      robot = new Robot();
      robot.setAutoDelay(50);
      robot.setAutoWaitForIdle(true);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @After
  public void tearDown() {
    robot.keyRelease(KeyEvent.VK_UP);
    robot.keyRelease(KeyEvent.VK_DOWN);
    robot.keyRelease(KeyEvent.VK_RIGHT);
    robot.keyRelease(KeyEvent.VK_LEFT);
    robot.keyRelease(KeyEvent.VK_ESCAPE);
  }

  @Test
  public void shouldMovePlayerUpWhenKeyPressed() throws InterruptedException {
    Player player = model.getPlayer();
    assertThat(player.isNotMoving(), equalTo(true));

    robot.delay(50);
    robot.keyPress(KeyEvent.VK_UP);
    robot.delay(50);
    robot.keyRelease(KeyEvent.VK_UP);

    assertThat(player.getVelocityX(), equalTo(0f));
    assertThat(player.getVelocityY(), lessThan(0f));
  }

  @Test
  public void shouldMovePlayerDownWhenKeyPressed() throws InterruptedException {
    Player player = model.getPlayer();
    assertThat(player.isNotMoving(), equalTo(true));

    robot.delay(50);
    robot.keyPress(KeyEvent.VK_DOWN);
    robot.delay(50);
    robot.keyRelease(KeyEvent.VK_DOWN);

    assertThat(player.getVelocityX(), equalTo(0f));
    assertThat(player.getVelocityY(), greaterThan(0f));
  }

  @Test
  public void shouldMovePlayerRightWhenKeyPressed() throws InterruptedException {
    Player player = model.getPlayer();
    assertThat(player.isNotMoving(), equalTo(true));

    robot.delay(50);
    robot.keyPress(KeyEvent.VK_RIGHT);
    robot.delay(50);
    robot.keyRelease(KeyEvent.VK_DOWN);

    assertThat(player.getVelocityX(), greaterThan(0f));
    assertThat(player.getVelocityY(), equalTo(0f));
  }

  @Test
  public void shouldMovePlayerLeftWhenKeyPressed() throws InterruptedException {
    Player player = model.getPlayer();
    assertThat(player.isNotMoving(), equalTo(true));

    robot.delay(50);
    robot.keyPress(KeyEvent.VK_LEFT);
    robot.delay(50);
    robot.keyRelease(KeyEvent.VK_DOWN);

    assertThat(player.getVelocityX(), lessThan(0f));
    assertThat(player.getVelocityY(), equalTo(0f));
  }

  @Test
  public void shouldFinishGameOnEscapePressed() throws InterruptedException {
    model.startGame();

    robot.delay(50);
    robot.keyPress(KeyEvent.VK_ESCAPE);
    robot.delay(50);
    robot.keyRelease(KeyEvent.VK_ESCAPE);

    assertThat(model.isRunning(), equalTo(false));
  }
}
