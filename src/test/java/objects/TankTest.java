package objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//TODO: figure out proper tests for tank class
public class TankTest {

  private Tank tank;
  final float BULLET_VELOCITY = 2f;

  @Before
  public void setUp() throws Exception {
    tank = new Tank(null);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void shouldFireBullet() throws Exception {
    tank.movementDirection = DynamicObject.Direction.DOWN;

    tank.shoot();

    assertThat(tank.isShooting(), equalTo(true));
    assertThat(tank.getBullet().getVelocityX(), equalTo(0f));
    assertThat(tank.getBullet().getVelocityY(), equalTo(BULLET_VELOCITY));
  }

  @Test
  public void shouldNotChangeDirectionOfBulletTryingToShootAgain() throws Exception {
    tank.getBullet().moveDown();
    tank.shooting = true;
    tank.moveRight();
    tank.shoot();

    assertThat(tank.getBullet().getVelocityX(), equalTo(0f));
    assertThat(tank.getBullet().getVelocityY(), equalTo(BULLET_VELOCITY));
  }
}
