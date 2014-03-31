package objects;

public class Tank extends DynamicObject {

  private Bullet bullet;

  protected boolean shooting;

  public Tank() {
    baseVelocity = 1;
    bullet = new Bullet();
    shooting = false;
  }

  public Bullet getBullet() {
    return bullet;
  }

  public boolean isShooting() {
    return shooting;
  }
}
