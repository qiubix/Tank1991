package objects;

public class DestroyableObject extends GameObjectDecorator {

  public DestroyableObject(GameObject gameObject) {
    super(gameObject);
  }

  @Override
  public void update(long elapsedTime) {
    gameObject.update(elapsedTime);
  }
}
