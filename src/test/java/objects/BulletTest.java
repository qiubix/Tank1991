package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BulletTest {

  private final int BASE_BULLET_VELOCITY = 2;

  //REVIEW: maybe tests for movement are redundant, because this is already tested in DynamicObjectTest
  private Bullet bullet;

  @Before
  public void setUp() throws Exception {
    bullet = new Bullet();
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void shouldMoveUp() throws Exception {
    bullet.moveUp();

    assertThat(bullet.getVelocityX(), equalTo(0));
    assertThat(bullet.getVelocityY(), equalTo(-BASE_BULLET_VELOCITY));
    assertThat(bullet.movementDirection, equalTo(DynamicObject.Direction.UP));
//    assertEquals(-BASE_BULLET_VELOCITY, bullet.getVelocityY());
//    assertEquals(0, bullet.getVelocityX());
//    assertEquals(DynamicObject.Direction.UP, bullet.movementDirection);
  }

  @Test
  public void shouldMoveDown() throws Exception {
    bullet.moveDown();

    assertThat(bullet.getVelocityX(), equalTo(0));
    assertThat(bullet.getVelocityY(), equalTo(BASE_BULLET_VELOCITY));
    assertThat(bullet.movementDirection, equalTo(DynamicObject.Direction.DOWN));
//    assertEquals(BASE_BULLET_VELOCITY, bullet.getVelocityY());
//    assertEquals(0, bullet.getVelocityX());
//    assertEquals(DynamicObject.Direction.DOWN, bullet.movementDirection);
  }

  @Test
  public void shouldMoveLeft() throws Exception {
    bullet.moveLeft();

    assertThat(bullet.getVelocityX(), equalTo(-BASE_BULLET_VELOCITY));
    assertThat(bullet.getVelocityY(), equalTo(0));
    assertThat(bullet.movementDirection, equalTo(DynamicObject.Direction.LEFT));
//    assertEquals(-BASE_BULLET_VELOCITY, bullet.getVelocityX());
//    assertEquals(0, bullet.getVelocityY());
//    assertEquals(DynamicObject.Direction.LEFT, bullet.movementDirection);
  }

  @Test
  public void shouldMoveRight() throws Exception {
    bullet.moveRight();

    assertThat(bullet.getVelocityX(), equalTo(BASE_BULLET_VELOCITY));
    assertThat(bullet.getVelocityY(), equalTo(0));
    assertThat(bullet.movementDirection, equalTo(DynamicObject.Direction.RIGHT));
//    assertEquals(BASE_BULLET_VELOCITY, bullet.getVelocityX());
//    assertEquals(0, bullet.getVelocityY());
//    assertEquals(DynamicObject.Direction.RIGHT, bullet.movementDirection);
  }
}
