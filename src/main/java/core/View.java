package core;

import graphics.ScreenManager;
import objects.Player;

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
    this.mainWindow = new MainWindow(width, height);
    this.screenManager = new ScreenManager(mainWindow);
  }

  public MainWindow getMainWindow() {
    return mainWindow;
  }

  @Override
  public void update(Observable observed, Object arg) {
    int newLevelNumber = model.getCurrentLevelNumber();
    mainWindow.setCurrentLevelNumber(newLevelNumber);
    draw();
  }

  private void draw() {
    Graphics2D graphics = screenManager.getGraphics();

    drawPlayer(graphics);

    graphics.dispose();
    screenManager.update();
  }

  private void drawPlayer(Graphics2D graphics) {
    Player player = model.getPlayer();
    graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), null);
  }
}
