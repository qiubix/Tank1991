package core;

import objects.Player;

public class Model {
  private GameState gameState;
  private Player player;

  public Model() {
    this.gameState = GameState.PAUSE;
    this.player = new Player();
    this.player.setPositionX(20);
    this.player.setPositionY(20);
  }

  public GameState getGameState() {
    return gameState;
  }

  public void startGame() {
    this.gameState = GameState.RUNNING;
  }

  public Player getPlayer() {
    return player;
  }
}
