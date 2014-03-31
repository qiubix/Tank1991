package objects;

public class Bullet extends DynamicObject {

  public Bullet() {
    baseVelocity = 2;
  }

  @Override
  public void update(long elapsedTime) {
    super.update(elapsedTime);
  }
}
