package objects;

abstract class DynamicObject extends GameObject {
  protected int velocityX;
  protected int velocityY;

  public enum Direction {
    UP, DOWN, LEFT, RIGHT
  }
  protected Direction movementDirection;

  protected int baseVelocity;

  protected DynamicObject() {
    baseVelocity = 1;
  }

  public int getVelocityX() {
    return velocityX;
  }

  public int getVelocityY() {
    return velocityY;
  }

  public void moveUp() {
    this.movementDirection = Direction.UP;
    this.velocityX = 0;
    this.velocityY = -baseVelocity;
  }

  public void moveDown() {
    this.movementDirection = Direction.DOWN;
    this.velocityX = 0;
    this.velocityY = baseVelocity;
  }

  public void moveLeft() {
    this.movementDirection = Direction.LEFT;
    this.velocityY = 0;
    this.velocityX = -baseVelocity;
  }

  public void moveRight() {
    this.movementDirection = Direction.RIGHT;
    this.velocityY = 0;
    this.velocityX = baseVelocity;
  }

  public void move(Direction direction) {
    switch (direction) {
      case UP: moveUp(); break;
      case DOWN: moveDown(); break;
      case LEFT: moveLeft(); break;
      case RIGHT: moveRight(); break;
    }
  }

  public void update(long elapsedTime) {
    int newPositionX = (int) (positionX + velocityX * elapsedTime);
    int newPositionY = (int) (positionY + velocityY * elapsedTime);
    positionX = newPositionX;
    positionY = newPositionY;
  }
}
