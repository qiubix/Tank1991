package core;

import objects.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
//    assertEquals(GameState.PAUSE, model.getGameState());
    assertThat(model.getGameState(), equalTo(GameState.PAUSE));
  }

  @Test
  public void shouldNotBeRunningBeforeStart() {
    assertThat(model.isRunning(), equalTo(false));
  }

  @Test
  public void shouldBeRunningAfterStart() {
    model.startGame();

    assertThat(model.isRunning(), equalTo(true));
  }

  @Test
  public void shouldStartGame() throws Exception {
    final int PLAYER_STARTING_POSITION_X = 20;
    final int PLAYER_STARTING_POSITION_Y = 20;

    model.startGame();

    assertThat(model.getGameState(), equalTo(GameState.RUNNING));
    assertThat(model.getPlayer().getPositionX(), equalTo(PLAYER_STARTING_POSITION_X));
    assertThat(model.getPlayer().getPositionY(), equalTo(PLAYER_STARTING_POSITION_Y));
    assertThat(model.getCurrentLevelNumber(), equalTo(1));
    assertThat(model.getPlayerPoints(), equalTo(0));
    assertThat(model.getEnemiesToBeat(), equalTo(10));
    assertThat(model.getPlayerLifes(), equalTo(3));
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
    assertThat(model.getGameState(), equalTo(GameState.PAUSE));
    Player player = model.getPlayer();
    int playerPositionX = player.getPositionX();
    int playerPositionY = player.getPositionY();
    long elapsedTime = 10;
    model.update(elapsedTime);
    assertThat(model.getPlayer().getPositionX(), equalTo(playerPositionX));
    assertThat(model.getPlayer().getPositionY(), equalTo(playerPositionY));
//    assertEquals(playerPositionX, model.getPlayer().getPositionX());
//    assertEquals(playerPositionY, model.getPlayer().getPositionY());
  }

  @Test
  public void shouldUpdateModelWhenGameIsRunning() {
    model.startGame();
    assertThat(model.getGameState(), equalTo(GameState.RUNNING));
    Player player = model.getPlayer();

    player.moveRight();
    int playerPositionX = player.getPositionX();
    int playerPositionY = player.getPositionY();
    long elapsedTime = 10;
    model.update(elapsedTime);
    assertThat(model.getPlayer().getPositionX(), not(equalTo(playerPositionX)));
    assertThat(model.getPlayer().getPositionY(), equalTo(playerPositionY));

    player.moveDown();
    playerPositionX = player.getPositionX();
    playerPositionY = player.getPositionY();
    model.update(elapsedTime);
    assertThat(model.getPlayer().getPositionX(), equalTo(playerPositionX));
    assertThat(model.getPlayer().getPositionY(), not(equalTo(playerPositionY)));
//    assertEquals(playerPositionX, model.getPlayer().getPositionX());
//    assertNotEquals(playerPositionY, model.getPlayer().getPositionY());
  }

  @Test
  public void shouldFinishGame() {
    model.finishGame();

    assertThat(model.getGameState(), equalTo(GameState.FINISHED));
  }
}
