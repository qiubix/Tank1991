package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BulletTest {

  private final float BASE_BULLET_VELOCITY = 2f;

  //REVIEW: maybe tests for movement are redundant, because this is already tested in DynamicObjectTest
  private Bullet bullet;

  @Before
  public void setUp() throws Exception {
    bullet = new Bullet(null);
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void shouldMoveUp() throws Exception {
    bullet.moveUp();

    assertThat(bullet.getVelocityX(), equalTo(0f));
    assertThat(bullet.getVelocityY(), equalTo(-BASE_BULLET_VELOCITY));
    assertThat(bullet.movementDirection, equalTo(DynamicObject.Direction.UP));
  }

  @Test
  public void shouldMoveDown() throws Exception {
    bullet.moveDown();

    assertThat(bullet.getVelocityX(), equalTo(0f));
    assertThat(bullet.getVelocityY(), equalTo(BASE_BULLET_VELOCITY));
    assertThat(bullet.movementDirection, equalTo(DynamicObject.Direction.DOWN));
  }

  @Test
  public void shouldMoveLeft() throws Exception {
    bullet.moveLeft();

    assertThat(bullet.getVelocityX(), equalTo(-BASE_BULLET_VELOCITY));
    assertThat(bullet.getVelocityY(), equalTo(0f));
    assertThat(bullet.movementDirection, equalTo(DynamicObject.Direction.LEFT));
  }

  @Test
  public void shouldMoveRight() throws Exception {
    bullet.moveRight();

    assertThat(bullet.getVelocityX(), equalTo(BASE_BULLET_VELOCITY));
    assertThat(bullet.getVelocityY(), equalTo(0f));
    assertThat(bullet.movementDirection, equalTo(DynamicObject.Direction.RIGHT));
  }
}
