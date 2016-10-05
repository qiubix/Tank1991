package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PlayerTest {

  private Player player;
  final int POSITION_X = 10;
  final int POSITION_Y = 20;

  @Before
  public void setUp() throws Exception {
    player = new Player(null);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void shouldSetPosition() throws Exception {
    player.setPositionX(POSITION_X);
    player.setPositionY(POSITION_Y);

//    assertEquals(POSITION_X, player.getPositionX());
//    assertEquals(POSITION_Y, player.getPositionY());
    assertThat(player.getPositionX(), equalTo(POSITION_X));
    assertThat(player.getPositionY(), equalTo(POSITION_Y));
  }

  @Test
  public void shoudCreatePlayer() {
    Player newPlayer = (Player) player.create();
//    assertNotNull(newPlayer);
//    assertEquals(POSITION_X, newPlayer.getPositionX());
//    assertEquals(POSITION_Y, newPlayer.getPositionY());

    assertThat(newPlayer, notNullValue());
    assertThat(newPlayer.getPositionX(), equalTo(POSITION_X));
    assertThat(newPlayer.getPositionY(), equalTo(POSITION_Y));
  }
}
