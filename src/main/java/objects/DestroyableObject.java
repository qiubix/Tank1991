package objects;

import java.awt.*;

public class DestroyableObject extends GameObjectDecorator {

  public DestroyableObject(GameObject gameObject, Image image) {
    super(gameObject, image);
  }

  @Override
  public void update(long elapsedTime) {
    gameObject.update(elapsedTime);
  }
}
