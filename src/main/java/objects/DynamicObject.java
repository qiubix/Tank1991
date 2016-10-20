package objects;

import java.awt.*;

abstract class DynamicObject extends GameObject {
  protected float velocityX;
  protected float velocityY;

  public enum Direction {
    UP, DOWN, LEFT, RIGHT
  }
  protected Direction movementDirection;

  protected float baseVelocity;

  protected DynamicObject(Image image) {
    super(image);
    baseVelocity = 0.3f;
  }

  public float getVelocityX() {
    return velocityX;
  }

  public void setVelocityX(float velocityX) {
    this.velocityX = velocityX;
  }

  public float getVelocityY() {
    return velocityY;
  }

  public void setVelocityY(float velocityY) {
    this.velocityY = velocityY;
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

  public abstract void collide();

  public void update(long elapsedTime) {
    int newPositionX = (int) (positionX + velocityX * elapsedTime);
    int newPositionY = (int) (positionY + velocityY * elapsedTime);
    positionX = newPositionX;
    positionY = newPositionY;
  }
}
