package objects;

import java.awt.*;

abstract class GameObjectDecorator extends GameObject {

  protected GameObject gameObject;

  public GameObjectDecorator(GameObject gameObject, Image image) {
    super(image);
    this.gameObject = gameObject;
  }

  @Override
  public int getPositionX() {
    return gameObject.getPositionX();
  }

  @Override
  public int getPositionY() {
    return gameObject.getPositionY();
  }

  @Override
  public void update(long elapsedTime) {
    gameObject.update(elapsedTime);
  }
}
