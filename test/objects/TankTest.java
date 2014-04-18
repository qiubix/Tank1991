package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//TODO: figure out proper tests for tank class
public class TankTest {

  private Tank tank;

  @Before
  public void setUp() throws Exception {
    tank = new Tank();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void shouldFireBullet() throws Exception {
    final int BULLET_VELOCITY = 2;
    tank.movementDirection = DynamicObject.Direction.DOWN;
    tank.shoot();
    assertTrue(tank.isShooting());
    assertEquals(0, tank.getBullet().getVelocityX());
    assertEquals(BULLET_VELOCITY, tank.getBullet().getVelocityY());
  }

  @Test
  public void shouldNotChangeDirectionOfBulletTryingToShootAgain() throws Exception {
    final int BULLET_VELOCITY = 2;
    tank.getBullet().moveDown();
    tank.shooting = true;
    tank.moveRight();
    tank.shoot();
    assertEquals(0, tank.getBullet().getVelocityX());
    assertEquals(BULLET_VELOCITY, tank.getBullet().getVelocityY());
  }
}
