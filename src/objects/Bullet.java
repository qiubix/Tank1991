package objects;

public class Bullet extends DynamicObject {

  public Bullet() {
    baseVelocity = 2;
  }

  @Override
  public void update(long elapsedTime) {
    super.update(elapsedTime);
  }

  @Override
  public void collide() {
    velocityX = 0;
    velocityY = 0;
  }
}
