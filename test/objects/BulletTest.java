package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BulletTest {

  private final int baseBulletVelocity = 2;

  //REVIEW: maybe tests should be set up
  //REVIEW: maybe tests for movement are redundant, because this is already tested in DynamicObjectTest
  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void shouldMoveUp() throws Exception {
    Bullet bullet = new Bullet();
    bullet.moveUp();
    assertEquals(-baseBulletVelocity, bullet.getVelocityY());
    assertEquals(0, bullet.getVelocityX());
    assertEquals(DynamicObject.Direction.UP, bullet.movementDirection);
  }

  @Test
  public void shouldMoveDown() throws Exception {
    Bullet bullet = new Bullet();
    bullet.moveDown();
    assertEquals(baseBulletVelocity, bullet.getVelocityY());
    assertEquals(0, bullet.getVelocityX());
    assertEquals(DynamicObject.Direction.DOWN, bullet.movementDirection);
  }

  @Test
  public void shouldMoveLeft() throws Exception {
    Bullet bullet = new Bullet();
    bullet.moveLeft();
    assertEquals(-baseBulletVelocity, bullet.getVelocityX());
    assertEquals(0, bullet.getVelocityY());
    assertEquals(DynamicObject.Direction.LEFT, bullet.movementDirection);
  }

  @Test
  public void shouldMoveRight() throws Exception {
    Bullet bullet = new Bullet();
    bullet.moveRight();
    assertEquals(baseBulletVelocity, bullet.getVelocityX());
    assertEquals(0, bullet.getVelocityY());
    assertEquals(DynamicObject.Direction.RIGHT, bullet.movementDirection);
  }
}
