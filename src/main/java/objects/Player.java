package objects;

import graphics.ImageLoader;

import java.awt.*;

public class Player extends Tank {

  public Player(Image image) {
    super(image);
  }

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
      Player newPlayer = new Player(ImageLoader.loadImage("player1.png"));
      newPlayer.setPositionX(10);
      newPlayer.setPositionY(20);
      return newPlayer;
    }
  }
}
