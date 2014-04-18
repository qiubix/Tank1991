package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicObjectTest {

  private DynamicObject dynamicObject;

  @Before
  public void setUp() throws Exception {
    dynamicObject = new Bullet();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void shouldMoveUp() throws Exception {
    dynamicObject.moveUp();
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 > dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.UP, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveDown() throws Exception {
    dynamicObject.moveDown();
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 < dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.DOWN, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveLeft() throws Exception {
    dynamicObject.moveLeft();
    assertTrue(0 > dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.LEFT, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveRight() throws Exception {
    dynamicObject.moveRight();
    assertTrue(0 < dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.RIGHT, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveInAnyDirection() throws Exception {
    //FIXME: remove duplication in tests

    dynamicObject.move(DynamicObject.Direction.UP);
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 > dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.UP, dynamicObject.movementDirection);

    dynamicObject.move(DynamicObject.Direction.DOWN);
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 < dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.DOWN, dynamicObject.movementDirection);

    dynamicObject.move(DynamicObject.Direction.LEFT);
    assertTrue(0 > dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.LEFT, dynamicObject.movementDirection);

    dynamicObject.move(DynamicObject.Direction.RIGHT);
    assertTrue(0 < dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.RIGHT, dynamicObject.movementDirection);
  }

  @Test
  public void shouldChangePositionAfterUpdateIfMoving() throws Exception {
    final int MOVEMENT_TIME = 10;
    dynamicObject.velocityX = 1;
    dynamicObject.movementDirection = DynamicObject.Direction.RIGHT;
    int currentPositionX = dynamicObject.getPositionX();
    dynamicObject.update(MOVEMENT_TIME);
    currentPositionX += MOVEMENT_TIME*dynamicObject.getVelocityX();
    assertEquals(currentPositionX, dynamicObject.getPositionX());
  }

  @Test
  public void shouldMoveWithConstantSpeed() throws Exception {
    final int MOVEMENT_TIME = 10;
    final int BASE_VELOCITY = 2;
    assertEquals(0, dynamicObject.getPositionX());
    assertEquals(0, dynamicObject.getPositionY());
    dynamicObject.moveDown();
    dynamicObject.update(MOVEMENT_TIME);
    assertEquals(0, dynamicObject.getPositionX());
    assertEquals(MOVEMENT_TIME * BASE_VELOCITY, dynamicObject.getPositionY());
  }

  @Test
  public void shouldTurn90Degrees() throws Exception {
    dynamicObject.moveRight();
    dynamicObject.moveDown();
    assertEquals(0, dynamicObject.getVelocityX());
    assertTrue(0 < dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.DOWN, dynamicObject.movementDirection);
  }

  @Test
  public void shouldTurn180Degrees() throws Exception {
    dynamicObject.moveRight();
    dynamicObject.moveLeft();
    assertTrue(0 > dynamicObject.getVelocityX());
    assertEquals(0, dynamicObject.getVelocityY());
    assertEquals(DynamicObject.Direction.LEFT, dynamicObject.movementDirection);
  }
}
