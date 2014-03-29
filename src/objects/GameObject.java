package objects;

abstract class GameObject {
  protected int positionX;
  protected int positionY;

  public int getPositionX() {
    return positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public enum Direction {
    UP, DOWN, LEFT, RIGHT
  }

  public abstract void update(long elapsedTime);
}
