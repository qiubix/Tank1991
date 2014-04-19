package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

  private Player player;

  @Before
  public void setUp() throws Exception {
    player = new Player();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void shouldSetPosition() throws Exception {
    final int POSITION_X = 10;
    final int POSITION_Y = 20;
    player.setPositionX(POSITION_X);
    player.setPositionY(POSITION_Y);
    assertEquals(POSITION_X, player.getPositionX());
    assertEquals(POSITION_Y, player.getPositionY());
  }
}
