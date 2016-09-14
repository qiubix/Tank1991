package core;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

  public static final int DEFAULT_MAIN_WINDOW_WIDTH = 1024;
  public static final int DEFAULT_MAIN_WINDOW_HEIGHT = 768;

  private MainWindow mainWindow;

  private Model model;

  public View(Model model) {
    this(model, DEFAULT_MAIN_WINDOW_WIDTH, DEFAULT_MAIN_WINDOW_HEIGHT);
  }

  public View(Model model, int width, int height) {
    this.model = model;
    mainWindow = new MainWindow(width, height);
  }

  public MainWindow getMainWindow() {
    return mainWindow;
  }

  @Override
  public void update(Observable observed, Object arg) {
    int newLevelNumber = model.getCurrentLevelNumber();
    mainWindow.setCurrentLevelNumber(newLevelNumber);
  }
}
