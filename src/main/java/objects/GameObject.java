package objects;

import java.awt.*;

public abstract class GameObject {
  protected int positionX;
  protected int positionY;

  protected Image image;

  public GameObject(Image image) {
    this.image = image;
  }

  public int getPositionX() {
    return positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public Image getImage() {
    return image;
  }

  public abstract void update(long elapsedTime);
}
