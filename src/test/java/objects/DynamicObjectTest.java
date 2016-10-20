package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DynamicObjectTest {

  private DynamicObject dynamicObject;

  @Before
  public void setUp() throws Exception {
    dynamicObject = new Bullet(null);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void shouldMoveUp() throws Exception {
    dynamicObject.moveUp();

    assertThat(dynamicObject.getVelocityX(), equalTo(0f));
    assertThat(dynamicObject.getVelocityY(), lessThan(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.UP));
//    assertEquals(0, dynamicObject.getVelocityX());
//    assertTrue(0 > dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.UP, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveDown() throws Exception {
    dynamicObject.moveDown();

    assertThat(dynamicObject.getVelocityX(), equalTo(0f));
    assertThat(dynamicObject.getVelocityY(), greaterThan(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.DOWN));
//    assertEquals(0, dynamicObject.getVelocityX());
//    assertTrue(0 < dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.DOWN, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveLeft() throws Exception {
    dynamicObject.moveLeft();

    assertThat(dynamicObject.getVelocityX(), lessThan(0f));
    assertThat(dynamicObject.getVelocityY(), equalTo(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.LEFT));
//    assertTrue(0 > dynamicObject.getVelocityX());
//    assertEquals(0, dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.LEFT, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveRight() throws Exception {
    dynamicObject.moveRight();

    assertThat(dynamicObject.getVelocityX(), greaterThan(0f));
    assertThat(dynamicObject.getVelocityY(), equalTo(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.RIGHT));
//    assertTrue(0 < dynamicObject.getVelocityX());
//    assertEquals(0, dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.RIGHT, dynamicObject.movementDirection);
  }

  @Test
  public void shouldMoveInAnyDirection() throws Exception {
    //FIXME: remove duplication in tests

    dynamicObject.move(DynamicObject.Direction.UP);

    assertThat(dynamicObject.getVelocityX(), equalTo(0f));
    assertThat(dynamicObject.getVelocityY(), lessThan(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.UP));
//    assertEquals(0, dynamicObject.getVelocityX());
//    assertTrue(0 > dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.UP, dynamicObject.movementDirection);

    dynamicObject.move(DynamicObject.Direction.DOWN);

    assertThat(dynamicObject.getVelocityX(), equalTo(0f));
    assertThat(dynamicObject.getVelocityY(), greaterThan(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.DOWN));
//    assertEquals(0, dynamicObject.getVelocityX());
//    assertTrue(0 < dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.DOWN, dynamicObject.movementDirection);

    dynamicObject.move(DynamicObject.Direction.LEFT);

    assertThat(dynamicObject.getVelocityX(), lessThan(0f));
    assertThat(dynamicObject.getVelocityY(), equalTo(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.LEFT));
//    assertTrue(0 > dynamicObject.getVelocityX());
//    assertEquals(0, dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.LEFT, dynamicObject.movementDirection);

    dynamicObject.move(DynamicObject.Direction.RIGHT);

    assertThat(dynamicObject.getVelocityX(), greaterThan(0f));
    assertThat(dynamicObject.getVelocityY(), equalTo(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.RIGHT));
//    assertTrue(0 < dynamicObject.getVelocityX());
//    assertEquals(0, dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.RIGHT, dynamicObject.movementDirection);
  }

  @Test
  public void shouldChangePositionAfterUpdateIfMoving() throws Exception {
    final int MOVEMENT_TIME = 10;
    dynamicObject.velocityX = 1;
    dynamicObject.movementDirection = DynamicObject.Direction.RIGHT;
    int currentPositionX = dynamicObject.getPositionX();

    dynamicObject.update(MOVEMENT_TIME);

    currentPositionX += MOVEMENT_TIME * dynamicObject.getVelocityX();
//    assertEquals(currentPositionX, dynamicObject.getPositionX());
    assertThat(dynamicObject.getPositionX(), equalTo(currentPositionX));
  }

  @Test
  public void shouldMoveWithConstantSpeed() throws Exception {
    final int MOVEMENT_TIME = 10;
    final float BASE_VELOCITY = 2f;
//    assertEquals(0, dynamicObject.getPositionX());
//    assertEquals(0, dynamicObject.getPositionY());
    assertThat(dynamicObject.getPositionX(), equalTo(0));
    assertThat(dynamicObject.getPositionY(), equalTo(0));

    dynamicObject.moveDown();
    dynamicObject.update(MOVEMENT_TIME);

//    assertEquals(0, dynamicObject.getPositionX());
//    assertEquals(MOVEMENT_TIME * BASE_VELOCITY, dynamicObject.getPositionY());
    assertThat(dynamicObject.getPositionX(), equalTo(0));
    assertThat(dynamicObject.getPositionY(), equalTo((int) (MOVEMENT_TIME * BASE_VELOCITY)));
  }

  @Test
  public void shouldTurn90Degrees() throws Exception {
    dynamicObject.moveRight();
    dynamicObject.moveDown();

//    assertEquals(0, dynamicObject.getVelocityX());
//    assertTrue(0 < dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.DOWN, dynamicObject.movementDirection);
    assertThat(dynamicObject.getVelocityX(), equalTo(0f));
    assertThat(dynamicObject.getVelocityY(), greaterThan(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.DOWN));
  }

  @Test
  public void shouldTurn180Degrees() throws Exception {
    dynamicObject.moveRight();
    dynamicObject.moveLeft();
//    assertTrue(0 > dynamicObject.getVelocityX());
//    assertEquals(0, dynamicObject.getVelocityY());
//    assertEquals(DynamicObject.Direction.LEFT, dynamicObject.movementDirection);
    assertThat(dynamicObject.getVelocityX(), lessThan(0f));
    assertThat(dynamicObject.getVelocityY(), equalTo(0f));
    assertThat(dynamicObject.movementDirection, equalTo(DynamicObject.Direction.LEFT));
  }

  @Test
  public void shouldStopWhenColliding() throws Exception {
    dynamicObject.moveRight();

    dynamicObject.collide();

//    assertEquals(0, dynamicObject.getVelocityX());
//    assertEquals(0, dynamicObject.getVelocityY());
    assertThat(dynamicObject.getVelocityX(), equalTo(0f));
    assertThat(dynamicObject.getVelocityY(), equalTo(0f));
  }
}
