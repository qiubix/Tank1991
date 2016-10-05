package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class ScreenManager {

  JFrame window;

  public ScreenManager(JFrame window) {
    this.window = window;
    this.window.createBufferStrategy(2);
  }

  public Graphics2D getGraphics() {
//    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//    graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

    BufferStrategy bufferStrategy = window.getBufferStrategy();
    Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    return graphics;
  }

  public void update() {
    BufferStrategy bufferStrategy = window.getBufferStrategy();
    bufferStrategy.show();
  }
}
