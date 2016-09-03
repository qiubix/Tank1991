package objects;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//TODO: tests for destroying objects
public class DestroyableObjectTest {
  @Test
  public void shouldUpdateDecoratedObject() throws Exception {
    DestroyableObject destroyableObject = new DestroyableObject(new Bullet());
    destroyableObject.update(10);
    //TODO: finish test
  }
}
