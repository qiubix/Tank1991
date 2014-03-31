package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BulletTest {
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
    assertEquals(-1, bullet.getVelocityY());
    assertEquals(0, bullet.getVelocityX());
    assertEquals(GameObject.Direction.UP, bullet.movementDirection);
  }

  @Test
  public void shouldMoveDown() throws Exception {
    Bullet bullet = new Bullet();
    bullet.moveDown();
    assertEquals(1, bullet.getVelocityY());
    assertEquals(0, bullet.getVelocityX());
    assertEquals(GameObject.Direction.DOWN, bullet.movementDirection);
  }

  @Test
  public void shouldMoveLeft() throws Exception {
    Bullet bullet = new Bullet();
    bullet.moveLeft();
    assertEquals(-1, bullet.getVelocityX());
    assertEquals(0, bullet.getVelocityY());
    assertEquals(GameObject.Direction.LEFT, bullet.movementDirection);
  }

  @Test
  public void shouldMoveRight() throws Exception {
    Bullet bullet = new Bullet();
    bullet.moveRight();
    assertEquals(1, bullet.getVelocityX());
    assertEquals(0, bullet.getVelocityY());
    assertEquals(GameObject.Direction.RIGHT, bullet.movementDirection);
  }
}
