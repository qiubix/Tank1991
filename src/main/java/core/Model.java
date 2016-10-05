package core;

import objects.Player;

import java.util.Observable;

public class Model extends Observable {
  private GameState gameState;
  private Player player;
  private int currentLevelNumber;
  private int playerPoints;
  private int enemiesToBeat;
  private int playerLifes;

  public Model() {
    this.gameState = GameState.PAUSE;
    this.player = (Player) Player.create();
    this.player.setPositionX(20);
    this.player.setPositionY(20);
  }

  public GameState getGameState() {
    return gameState;
  }

  public void startGame() {
    this.gameState = GameState.RUNNING;
    this.currentLevelNumber = 1;
    this.playerPoints = 0;
    this.enemiesToBeat = 10;
    this.playerLifes = 3;
  }

  public Player getPlayer() {
    return player;
  }

  public void update(long elapsedTime) {
    if(gameState != GameState.PAUSE) {
      player.update(elapsedTime);
    }
    setChanged();
    notifyObservers();
  }

  public int getCurrentLevelNumber() {
    return currentLevelNumber;
  }

  public int getPlayerPoints() {
    return playerPoints;
  }

  public int getEnemiesToBeat() {
    return enemiesToBeat;
  }

  public int getPlayerLifes() {
    return playerLifes;
  }

  public void setCurrentLevelNumber(int currentLevelNumber) {
    this.currentLevelNumber = currentLevelNumber;
  }
}
