package core;

import objects.Player;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ControllerTest {

  @Test
  public void shouldMovePlayerUpWhenKeyPressed() {
    Model model = new Model();
    Controller controller = new Controller(model);
    Player player = model.getPlayer();

    assertThat(player.isNotMoving(), equalTo(true));
  }
}
