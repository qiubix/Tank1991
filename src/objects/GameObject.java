package objects;

abstract class GameObject {
  protected int positionX;
  protected int positionY;

  //FIXME: use Decorator pattern
  private boolean destroyable;

  public GameObject(boolean destroyable) {
    this.destroyable = destroyable;
  }

  public int getPositionX() {
    return positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public boolean isDestroyable() {
    return destroyable;
  }

  public abstract void update(long elapsedTime);
}
