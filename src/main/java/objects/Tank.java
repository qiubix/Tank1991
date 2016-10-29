package objects;

import java.awt.*;

public class Tank extends DynamicObject {

  private Bullet bullet;

  protected boolean shooting;

  public Tank(Image image) {
    super(image);
    baseVelocity = 0.5f;
    movementDirection = Direction.RIGHT;
    bullet = new Bullet(null);
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

  @Override
  public void collide() {
    velocityX = 0;
    velocityY = 0;
  }

  //TODO: make tank factory
}
