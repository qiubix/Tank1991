package utils;
import objects.Enemy;
import objects.GameObject;
import objects.Player;

public class GameObjectFactory {
  private static GameObjectFactory instance;

  private GameObjectFactory() {}

  public static GameObjectFactory getInstance() {
    if (instance == null) {
      instance = new GameObjectFactory();
    }
    return instance;
  }

  //TODO: implement factory method allowing to create object based on classInfo
  public GameObject create(Class classInfo) {
    return null;
  }

  public Player createPlayer() {
    Player newPlayer = (Player) Player.create();
    return newPlayer;
  }

  public Enemy createEnemy() {
    Enemy newEnemy = new Enemy(null);
    return newEnemy;
  }
}
