package objects;

import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicObjectTest {

  @Test
  public void shouldMoveUp() throws Exception {
    DynamicObject dynamicObject = new Bullet(true);
    dynamicObject.moveUp();
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 > dynamicObject.getVelocityY());
    assertEquals(GameObject.Direction.UP, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveDown() throws Exception {
    DynamicObject dynamicObject = new Bullet(true);
    dynamicObject.moveDown();
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 < dynamicObject.getVelocityY());
    assertEquals(GameObject.Direction.DOWN, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveLeft() throws Exception {
    DynamicObject dynamicObject = new Bullet(true);
    dynamicObject.moveLeft();
    assertTrue(0 > dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(GameObject.Direction.LEFT, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveRight() throws Exception {
    DynamicObject dynamicObject = new Bullet(true);
    dynamicObject.moveRight();
    assertTrue(0 < dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(GameObject.Direction.RIGHT, dynamicObject.movementDirection);
  }
  @Test
  public void shouldMoveWithConstantSpeed() throws Exception {
    DynamicObject dynamicObject = new Bullet(true);
    assertEquals(0, dynamicObject.getPositionX());
    assertEquals(0, dynamicObject.getPositionY());
    dynamicObject.moveDown();
    dynamicObject.update(10);
    assertEquals(0, dynamicObject.getPositionX());
    assertEquals(10, dynamicObject.getPositionY());
  }

  @Test
  public void shouldTurn90Degrees() throws Exception {
    DynamicObject dynamicObject = new Bullet(true);
    dynamicObject.moveRight();
    dynamicObject.moveDown();
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 < dynamicObject.getVelocityY());
    assertEquals(GameObject.Direction.DOWN, dynamicObject.movementDirection);
  }

  @Test
  public void shouldTurn180Degrees() throws Exception {
    DynamicObject dynamicObject = new Bullet(true);
    dynamicObject.moveRight();
    dynamicObject.moveLeft();
    assertTrue(0 > dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(GameObject.Direction.LEFT, dynamicObject.movementDirection);
  }
}
