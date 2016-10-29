package utils;

import objects.Enemy;
import objects.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GameObjectFactoryTest {

  GameObjectFactory factory;

  @Before
  public void setUp() throws Exception {
    factory = GameObjectFactory.getInstance();
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void shouldCreateAllTypesOfGameObjects() throws Exception {

  }

  //TODO: remove duplicate tests (PlayerTest.shouldCreatePlayer)
  @Test
  public void shouldCreatePlayer() throws Exception {
    final int PLAYER_STARTING_POSITION_X = 10;
    final int PLAYER_STARTING_POSITION_Y = 20;

    Player player = factory.createPlayer();

    assertThat(player, notNullValue());
    assertThat(player.getPositionX(), equalTo(PLAYER_STARTING_POSITION_X));
    assertThat(player.getPositionY(), equalTo(PLAYER_STARTING_POSITION_Y));
  }

  @Test
  public void shouldCreateEnemy() throws Exception {
    Enemy enemy = factory.createEnemy();
    assertThat(enemy, notNullValue());
    //TODO: test starting position
  }
}
