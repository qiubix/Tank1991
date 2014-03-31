package objects;

public class Tank extends DynamicObject {

  private Bullet bullet;

  protected boolean shooting;

  public Tank() {
    baseVelocity = 1;
    movementDirection = Direction.RIGHT;
    bullet = new Bullet();
    shooting = false;
  }

  public Bullet getBullet() {
    return bullet;
  }

  public boolean isShooting() {
    return shooting;
  }

  public void shoot() {
    if (!shooting) {
      shooting = true;
      bullet.move(movementDirection);
    }
  }
}
