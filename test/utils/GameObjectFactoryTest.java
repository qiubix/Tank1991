package utils;

import objects.Enemy;
import objects.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

  @Test
  public void shouldCreatePlayer() throws Exception {
    final int PLAYER_STARTING_POSITION_X = 20;
    final int PLAYER_STARTING_POSITION_Y = 20;
    Player player = factory.createPlayer();
    assertNotNull(player);
    assertEquals(PLAYER_STARTING_POSITION_X, player.getPositionX());
    assertEquals(PLAYER_STARTING_POSITION_Y, player.getPositionY());
  }

  @Test
  public void shouldCreateEnemy() throws Exception {
    Enemy enemy = factory.createEnemy();
    assertNotNull(enemy);
    //TODO: test starting position
  }
}
