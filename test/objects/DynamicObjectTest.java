package objects;

import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicObjectTest {

  //REVIEW: maybe tests should be set up

  @Test
  public void shouldMoveUp() throws Exception {
    DynamicObject dynamicObject = new Bullet();
    dynamicObject.moveUp();
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 > dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.UP, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveDown() throws Exception {
    DynamicObject dynamicObject = new Bullet();
    dynamicObject.moveDown();
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 < dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.DOWN, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveLeft() throws Exception {
    DynamicObject dynamicObject = new Bullet();
    dynamicObject.moveLeft();
    assertTrue(0 > dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.LEFT, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveRight() throws Exception {
    DynamicObject dynamicObject = new Bullet();
    dynamicObject.moveRight();
    assertTrue(0 < dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.RIGHT, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveInAnyDirection() throws Exception {
    //TODO: implement
  }

  @Test
  public void shouldMoveWithConstantSpeed() throws Exception {
    final int MOVEMENT_TIME = 10;
    final int BASE_VELOCITY = 2;
    DynamicObject dynamicObject = new Bullet();
    assertEquals(0, dynamicObject.getPositionX());
    assertEquals(0, dynamicObject.getPositionY());
    dynamicObject.moveDown();
    dynamicObject.update(MOVEMENT_TIME);
    assertEquals(0, dynamicObject.getPositionX());
    assertEquals(MOVEMENT_TIME * BASE_VELOCITY, dynamicObject.getPositionY());
  }

  @Test
  public void shouldTurn90Degrees() throws Exception {
    DynamicObject dynamicObject = new Bullet();
    dynamicObject.moveRight();
    dynamicObject.moveDown();
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 < dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.DOWN, dynamicObject.movementDirection);
  }

  @Test
  public void shouldTurn180Degrees() throws Exception {
    DynamicObject dynamicObject = new Bullet();
    dynamicObject.moveRight();
    dynamicObject.moveLeft();
    assertTrue(0 > dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.LEFT, dynamicObject.movementDirection);
  }
}
