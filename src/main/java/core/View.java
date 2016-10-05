package core;

import objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

  public static final int DEFAULT_MAIN_WINDOW_WIDTH = 1024;
  public static final int DEFAULT_MAIN_WINDOW_HEIGHT = 768;

  private MainWindow mainWindow;
//  private JFrame mainWindow;

  private Model model;

  public View(Model model) {
    this(model, DEFAULT_MAIN_WINDOW_WIDTH, DEFAULT_MAIN_WINDOW_HEIGHT);
  }

  public View(Model model, int width, int height) {
    this.model = model;
    mainWindow = new MainWindow(width, height);
//    mainWindow = new JFrame();
  }

  public MainWindow getMainWindow() {
    return (MainWindow) mainWindow;
  }

  @Override
  public void update(Observable observed, Object arg) {
    int newLevelNumber = model.getCurrentLevelNumber();
    mainWindow.setCurrentLevelNumber(newLevelNumber);
    draw();
  }

//  private GraphicsDevice graphicsDevice;

  private void draw() {
//    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//    graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
    mainWindow.createBufferStrategy(2);
    BufferStrategy bufferStrategy = mainWindow.getBufferStrategy();
    Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    Player player = model.getPlayer();
    graphics.drawImage(player.getImage(), 100, 100, null);
    graphics.dispose();
    bufferStrategy.show();
  }
}
