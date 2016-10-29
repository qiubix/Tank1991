package objects;

import java.awt.*;

public abstract class GameObject {
  protected float positionX;
  protected float positionY;

  protected int width;
  protected int height;

  protected Image image;

  public GameObject(Image image) {
    this.image = image;
    if (image != null) {
      this.width = image.getWidth(null);
      this.height = image.getHeight(null);
    }
  }

  public int getPositionX() {
    return (int) positionX;
  }

  public int getPositionY() {
    return (int) positionY;
  }

  public void setPositionX(float positionX) {
    this.positionX = positionX;
  }

  public void setPositionY(float positionY) {
    this.positionY = positionY;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Image getImage() {
    return image;
  }

  public abstract void update(long elapsedTime);
}
