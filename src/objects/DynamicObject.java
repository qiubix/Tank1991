package objects;

abstract class DynamicObject extends GameObject {
  protected int velocityX;
  protected int velocityY;

  protected Direction movementDirection;

  public int getVelocityX() {
    return velocityX;
  }

  public int getVelocityY() {
    return velocityY;
  }

  public abstract void moveUp();

  public abstract void moveDown();

  public abstract void moveLeft();

  public abstract void moveRight();

  public void update(long elapsedTime) {
    int newPositionX = (int) (positionX + velocityX * elapsedTime);
    int newPositionY = (int) (positionY + velocityY * elapsedTime);
  }
}
