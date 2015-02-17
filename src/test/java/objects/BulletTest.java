package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BulletTest {

  private final int baseBulletVelocity = 2;

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
    assertEquals(-baseBulletVelocity, bullet.getVelocityY());
    assertEquals(0, bullet.getVelocityX());
    assertEquals(DynamicObject.Direction.UP, bullet.movementDirection);
  }

  @Test
  public void shouldMoveDown() throws Exception {
    bullet.moveDown();
    assertEquals(baseBulletVelocity, bullet.getVelocityY());
    assertEquals(0, bullet.getVelocityX());
    assertEquals(DynamicObject.Direction.DOWN, bullet.movementDirection);
  }

  @Test
  public void shouldMoveLeft() throws Exception {
    bullet.moveLeft();
    assertEquals(-baseBulletVelocity, bullet.getVelocityX());
    assertEquals(0, bullet.getVelocityY());
    assertEquals(DynamicObject.Direction.LEFT, bullet.movementDirection);
  }

  @Test
  public void shouldMoveRight() throws Exception {
    bullet.moveRight();
    assertEquals(baseBulletVelocity, bullet.getVelocityX());
    assertEquals(0, bullet.getVelocityY());
    assertEquals(DynamicObject.Direction.RIGHT, bullet.movementDirection);
  }
}
