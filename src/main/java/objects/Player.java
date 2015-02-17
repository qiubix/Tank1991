package objects;

public class Player extends Tank {

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }

  public static GameObject create() {
    return PlayerFactory.create();
  }

  private static class PlayerFactory {
    public static GameObject create() {
      Player newPlayer = new Player();
      newPlayer.setPositionX(10);
      newPlayer.setPositionY(20);
      return newPlayer;
    }
  }
}
