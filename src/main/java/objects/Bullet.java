package objects;

import java.awt.*;

public class Bullet extends DynamicObject {

  public Bullet(Image image) {
    super(image);
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
