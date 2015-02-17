package objects;

public abstract class GameObject {
  protected int positionX;
  protected int positionY;

  public int getPositionX() {
    return positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public abstract void update(long elapsedTime);
}
