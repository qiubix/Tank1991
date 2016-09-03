package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

    assertThat(tank.isShooting(), equalTo(true));
    assertThat(tank.getBullet().getVelocityX(), equalTo(0));
    assertThat(tank.getBullet().getVelocityY(), equalTo(BULLET_VELOCITY));
//    assertTrue(tank.isShooting());
//    assertEquals(0, tank.getBullet().getVelocityX());
//    assertEquals(BULLET_VELOCITY, tank.getBullet().getVelocityY());
  }

  @Test
  public void shouldNotChangeDirectionOfBulletTryingToShootAgain() throws Exception {
    final int BULLET_VELOCITY = 2;
    tank.getBullet().moveDown();
    tank.shooting = true;
    tank.moveRight();
    tank.shoot();

    assertThat(tank.getBullet().getVelocityX(), equalTo(0));
    assertThat(tank.getBullet().getVelocityY(), equalTo(BULLET_VELOCITY));
//    assertEquals(0, tank.getBullet().getVelocityX());
//    assertEquals(BULLET_VELOCITY, tank.getBullet().getVelocityY());
  }
}
