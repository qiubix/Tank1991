package core;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ViewTest {

  private Model model = new Model();
  private View view = new View(model);

  private final int MAIN_WINDOW_WIDTH = 1024;
  private final int MAIN_WINDOW_HEIGHT = 768;

  @Test
  public void shouldShowMainFrame() {
    MainWindow mainWindow = view.getMainWindow();

    assertThat(mainWindow.isVisible(), equalTo(true));
  }

  @Test
  public void shouldMainWindowHaveDefaultDimentions() {
    MainWindow mainWindow = view.getMainWindow();

    assertThat(mainWindow.getWidth(), equalTo(View.DEFAULT_MAIN_WINDOW_WIDTH));
    assertThat(mainWindow.getHeight(), equalTo(View.DEFAULT_MAIN_WINDOW_HEIGHT));
  }

  @Test
  public void shouldMainWindowHaveSpecificDimentions() {
    View view = new View(model, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
    MainWindow mainWindow = view.getMainWindow();

    assertThat(mainWindow.getWidth(), equalTo(MAIN_WINDOW_WIDTH));
    assertThat(mainWindow.getHeight(), equalTo(MAIN_WINDOW_HEIGHT));
  }

  @Test
  public void shouldUpdateLevelNumberOnModelChange() {
    View view = new View(model);
    MainWindow mainWindow = view.getMainWindow();

    int currentLevelNumberInModel = model.getCurrentLevelNumber();
    int currentLevelNumberDisplayed = mainWindow.getCurrentLevelNumber();
    assertThat(currentLevelNumberDisplayed, equalTo(currentLevelNumberInModel));

    final int newLevelNumber = 2;
    model.setCurrentLevelNumber(newLevelNumber);

    view.update(model, view);

    currentLevelNumberDisplayed = mainWindow.getCurrentLevelNumber();
    assertThat(currentLevelNumberDisplayed, equalTo(newLevelNumber));
  }
}
