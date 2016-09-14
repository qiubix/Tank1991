package core;

public class View {

  public static final int DEFAULT_MAIN_WINDOW_WIDTH = 1024;
  public static final int DEFAULT_MAIN_WINDOW_HEIGHT = 768;

  private MainWindow mainWindow;

  public View(Model model) {
    mainWindow = new MainWindow(DEFAULT_MAIN_WINDOW_WIDTH, DEFAULT_MAIN_WINDOW_HEIGHT);
  }

  public View(Model model, int width, int height) {
    mainWindow = new MainWindow(width, height);
  }

  public MainWindow getMainWindow() {
    return mainWindow;
  }
}
