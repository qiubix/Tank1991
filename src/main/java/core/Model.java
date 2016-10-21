package core;

import objects.Player;
import level.Level;

import java.util.Observable;

public class Model extends Observable {
  private GameState gameState;
  private int currentLevelNumber;
  private int playerPoints;
  private int enemiesToBeat;
  private int playerLifes;

  private final int levelWidth = 1024;
  private final int levelHeight = 748;

  private Level level;

  public Model() {
    this.gameState = GameState.PAUSE;
    this.level = new Level(levelWidth, levelHeight);
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
    return level.getPlayer();
  }

  public void update(long elapsedTime) {
    if(gameState != GameState.PAUSE) {
      Player player = getPlayer();
      player.update(elapsedTime);
      if (player.getPositionX() >= getLevel().getWidth()) {
        player.collide();
      }
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

  public boolean isRunning() {
    return gameState.equals(GameState.RUNNING);
  }

  public void finishGame() {
    gameState = GameState.FINISHED;
  }

  public Level getLevel() {
    return level;
  }
}
