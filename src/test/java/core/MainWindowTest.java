package core;

import org.junit.Ignore;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MainWindowTest {

  @Ignore
  @Test
  public void shouldReturnCurrentLevelNumber() {
    MainWindow window = new MainWindow();

    assertThat(window.getCurrentLevelNumber(), equalTo(0));
  }

  @Ignore
  @Test
  public void shouldContainLevelPanel() {
    MainWindow window = new MainWindow();

    JPanel levelPanel = null;
    for (Component component : window.getContentPane().getComponents()) {
      if (component instanceof JPanel) {
        levelPanel = (JPanel) component;
      }
    }

    assertThat(levelPanel, notNullValue());
    assertThat(levelPanel.isShowing(), equalTo(true));
  }
}
