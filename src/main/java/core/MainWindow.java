package core;

import javax.swing.*;

public class MainWindow extends JFrame {

  private JPanel levelPanel = new JPanel();

  public MainWindow() {
    new MainWindow(View.DEFAULT_MAIN_WINDOW_WIDTH, View.DEFAULT_MAIN_WINDOW_HEIGHT);
  }

  public MainWindow(int window_width, int window_height) {
    super("Tank1991");
    initLevelPanel();
    this.getContentPane().add(levelPanel);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(window_width, window_height);
    this.setVisible(true);
  }

  private void initLevelPanel() {
    JLabel label = new JLabel("This game is gonna be a lot of fun!");
    JLabel levelNumber = new JLabel("0");
    levelPanel.add(label);
    levelPanel.add(levelNumber);
  }

  private JPanel getLevelPanel() {
    return levelPanel;
  }

  public int getCurrentLevelNumber() {
    int levelNumber = -1;
    JLabel label = (JLabel) levelPanel.getComponent(0);
    levelNumber = Integer.parseInt(label.getText());
    return levelNumber;
  }
}
