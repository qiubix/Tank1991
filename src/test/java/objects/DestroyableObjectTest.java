package objects;

import org.junit.Test;

//TODO: tests for destroying objects
public class DestroyableObjectTest {
  @Test
  public void shouldUpdateDecoratedObject() throws Exception {
    DestroyableObject destroyableObject = new DestroyableObject(new Bullet());
    destroyableObject.update(10);
    //TODO: finish test
  }
}
