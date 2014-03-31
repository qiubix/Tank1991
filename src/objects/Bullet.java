package objects;

public class Bullet extends DynamicObject {
  private final int baseVelocity = 1;

  public Bullet(boolean destroyable) {
    super(destroyable);
  }

  @Override
  public void moveUp() {
    this.movementDirection = Direction.UP;
    this.velocityX = 0;
    this.velocityY = -baseVelocity;
  }

  @Override
  public void moveDown() {
    this.movementDirection = Direction.DOWN;
    this.velocityX = 0;
    this.velocityY = baseVelocity;
  }

  @Override
  public void moveLeft() {
    this.movementDirection = Direction.LEFT;
    this.velocityY = 0;
    this.velocityX = -baseVelocity;
  }

  @Override
  public void moveRight() {
    this.movementDirection = Direction.RIGHT;
    this.velocityY = 0;
    this.velocityX = baseVelocity;
  }

  @Override
  public void update(long elapsedTime) {
    super.update(elapsedTime);
  }
}
