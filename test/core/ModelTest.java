package core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
