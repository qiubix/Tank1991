package objects;

import java.awt.*;

public abstract class GameObject {
  protected float positionX;
  protected float positionY;

  protected Image image;

  public GameObject(Image image) {
    this.image = image;
  }

  public int getPositionX() {
    return (int) positionX;
  }

  public int getPositionY() {
    return (int) positionY;
  }

  public Image getImage() {
    return image;
  }

  public abstract void update(long elapsedTime);
}
