package core;

import graphics.Drawable;
import graphics.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

  public static final int DEFAULT_MAIN_WINDOW_WIDTH = 1024;
  public static final int DEFAULT_MAIN_WINDOW_HEIGHT = 768;

  private MainWindow mainWindow;

  private ScreenManager screenManager;

  private Model model;

  public View(Model model) {
    this(model, DEFAULT_MAIN_WINDOW_WIDTH, DEFAULT_MAIN_WINDOW_HEIGHT);
  }

  public View(Model model, int width, int height) {
    this.model = model;
    this.mainWindow = new MainWindow(width, height, model.getLevel());
//    this.mainWindow.setLevelPanel(model.getLevel());
    this.screenManager = new ScreenManager(mainWindow);
    this.mainWindow.setVisible(true);
  }

  public MainWindow getMainWindow() {
    return mainWindow;
  }

  public JComponent getLevelPanel() {
    return mainWindow.getLevelPanel();
  }

  @Override
  public void update(Observable observed, Object arg) {
    int newLevelNumber = model.getCurrentLevelNumber();
    mainWindow.setCurrentLevelNumber(newLevelNumber);
    draw();
  }

  private void draw() {
    Graphics2D graphics = screenManager.getGraphics();
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    Drawable graphicsContext = (Drawable) getLevelPanel();
    graphicsContext.draw(graphics);
//    getLevelPanel().repaint();

    graphics.dispose();
    screenManager.update();
  }
}
