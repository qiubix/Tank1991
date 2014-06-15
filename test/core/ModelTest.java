package core;

import objects.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ModelTest {

  private Model model;

  @Before
  public void setUp() throws Exception {
    model = new Model();
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void shouldInitModelInPausedState() throws Exception {
    assertEquals(GameState.PAUSE, model.getGameState());
  }

  @Test
  public void shouldStartGame() throws Exception {
    final int PLAYER_STARTING_POSITION_X = 20;
    final int PLAYER_STARTING_POSITION_Y = 20;
    model.startGame();
    assertEquals(GameState.RUNNING, model.getGameState());
    assertEquals(PLAYER_STARTING_POSITION_X, model.getPlayer().getPositionX());
    assertEquals(PLAYER_STARTING_POSITION_Y, model.getPlayer().getPositionY());
    //TODO: finish test
  }

  @Test
  public void shouldInitLevel() throws Exception {
    //TODO: should load first level from file and put proper objects on map
  }

  @Test
  public void shouldReloadLevel() throws Exception {
    //TODO: should reset level and put all objects in starting positions
  }

  @Test
  public void shouldLoadNextLevel() throws Exception {
    //TODO: should increase level counter and load next level from file
  }

  @Test
  public void shouldNotUpdateModelWhenGameIsPaused() {
    assertEquals(GameState.PAUSE, model.getGameState());
    Player player = model.getPlayer();
    int playerPositionX = player.getPositionX();
    int playerPositionY = player.getPositionY();
    long elapsedTime = 10;
    model.update(elapsedTime);
    assertEquals(playerPositionX, model.getPlayer().getPositionX());
    assertEquals(playerPositionY, model.getPlayer().getPositionY());
  }

  @Test
  public void shouldUpdateModelWhenGameIsRunning() {
    model.startGame();
    assertEquals(GameState.RUNNING, model.getGameState());
    Player player = model.getPlayer();
    player.moveRight();
    int playerPositionX = player.getPositionX();
    int playerPositionY = player.getPositionY();
    long elapsedTime = 10;
    model.update(elapsedTime);
    assertNotEquals(playerPositionX, model.getPlayer().getPositionX());
    assertEquals(playerPositionY, model.getPlayer().getPositionY());
    player.moveDown();
    playerPositionX = player.getPositionX();
    playerPositionY = player.getPositionY();
    model.update(elapsedTime);
    assertEquals(playerPositionX, model.getPlayer().getPositionX());
    assertNotEquals(playerPositionY, model.getPlayer().getPositionY());
  }
}
