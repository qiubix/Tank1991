package objects;

abstract class GameObjectDecorator extends GameObject {

  protected GameObject gameObject;

  public GameObjectDecorator(GameObject gameObject) {
    this.gameObject = gameObject;
  }

  @Override
  public int getPositionX() {
    return gameObject.getPositionX();
  }

  @Override
  public int getPositionY() {
    return gameObject.getPositionY();
  }

  @Override
  public void update(long elapsedTime) {
    gameObject.update(elapsedTime);
  }
}
