package core;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

  private JPanel levelPanel = new JPanel();

  public MainWindow() {
    this(View.DEFAULT_MAIN_WINDOW_WIDTH, View.DEFAULT_MAIN_WINDOW_HEIGHT);
  }

  public MainWindow(int windowWidth, int windowHeight) {
    super("Tank1991");
    initLevelPanel(windowWidth, windowHeight);
    this.getContentPane().add(levelPanel);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(windowWidth, windowHeight);
    this.setBackground(Color.white);
    this.setVisible(true);
  }

  private void initLevelPanel(int windowWidth, int windowHeight) {
    JLabel label = new JLabel("This game is gonna be a lot of fun!");
    JLabel levelNumber = new JLabel("0");
    levelPanel.add(label);
    levelPanel.add(levelNumber);
    levelPanel.setSize(windowWidth, 20);
//    levelPanel.setBackground(Color.white);
    levelPanel.setOpaque(true);
  }

  public JPanel getLevelPanel() {
    return levelPanel;
  }

  public int getCurrentLevelNumber() {
    JLabel label = (JLabel) levelPanel.getComponent(1);
    return Integer.parseInt(label.getText());
  }

  public void setCurrentLevelNumber(int currentLevelNumber) {
    JLabel label = (JLabel) levelPanel.getComponent(1);
    label.setText(String.valueOf(currentLevelNumber));
  }
}
