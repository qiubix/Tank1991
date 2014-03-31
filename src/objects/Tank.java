package objects;

public class Tank extends DynamicObject {

  private int baseVelocity = 1;

  private Bullet bullet;

  protected boolean shooting;

  public Tank(boolean destroyable) {
    super(destroyable);
    bullet = new Bullet(false);
    shooting = false;
  }

  public Bullet getBullet() {
    return bullet;
  }

  public boolean isShooting() {
    return shooting;
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
}
