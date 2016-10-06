package core;

import objects.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
  }

  @Test
  public void shouldMovePlayerUpWhenKeyPressed() throws InterruptedException {
    Player player = model.getPlayer();
    assertThat(player.isNotMoving(), equalTo(true));

    robot.keyPress(KeyEvent.VK_UP);

    Thread.sleep(200);

    assertThat(player.getVelocityX(), equalTo(0));
    assertThat(player.getVelocityY(), lessThan(0));
  }

  @Test
  public void shouldMovePlayerDownWhenKeyPressed() throws InterruptedException {
    Player player = model.getPlayer();
    assertThat(player.isNotMoving(), equalTo(true));

    robot.keyPress(KeyEvent.VK_DOWN);

    Thread.sleep(200);

    assertThat(player.getVelocityX(), equalTo(0));
    assertThat(player.getVelocityY(), greaterThan(0));
  }

  @Test
  public void shouldMovePlayerRightWhenKeyPressed() throws InterruptedException {
    Player player = model.getPlayer();
    assertThat(player.isNotMoving(), equalTo(true));

    robot.keyPress(KeyEvent.VK_RIGHT);

    Thread.sleep(200);

    assertThat(player.getVelocityX(), greaterThan(0));
    assertThat(player.getVelocityY(), equalTo(0));
  }

  @Test
  public void shouldMovePlayerLeftWhenKeyPressed() throws InterruptedException {
    Player player = model.getPlayer();
    assertThat(player.isNotMoving(), equalTo(true));

    robot.keyPress(KeyEvent.VK_LEFT);

    Thread.sleep(200);

    assertThat(player.getVelocityX(), lessThan(0));
    assertThat(player.getVelocityY(), equalTo(0));
  }
}
