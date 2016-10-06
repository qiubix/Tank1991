package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

    assertThat(player.getPositionX(), equalTo(POSITION_X));
    assertThat(player.getPositionY(), equalTo(POSITION_Y));
  }

  @Test
  public void shoudCreatePlayer() {
    Player newPlayer = (Player) player.create();

    assertThat(newPlayer, notNullValue());
    assertThat(newPlayer.getPositionX(), equalTo(POSITION_X));
    assertThat(newPlayer.getPositionY(), equalTo(POSITION_Y));
  }

  @Test
  public void shouldReturnTrueWhenPlayerIsNotMoving() {
    player.setVelocityX(0);
    player.setVelocityY(0);

    assertThat(player.isNotMoving(), equalTo(true));
  }

  @Test
  public void shouldReturnFalseWhenPlayerIsMoving() {
    player.setVelocityX(0);
    player.setVelocityY(10);

    assertThat(player.isNotMoving(), equalTo(false));

    player.setVelocityX(3);
    player.setVelocityY(0);

    assertThat(player.isNotMoving(), equalTo(false));
  }
}
