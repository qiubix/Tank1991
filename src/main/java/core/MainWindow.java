package core;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

  private JPanel scorePanel = new JPanel();
  private JPanel levelPanel = new JPanel();

  public MainWindow() {
    this(View.DEFAULT_MAIN_WINDOW_WIDTH, View.DEFAULT_MAIN_WINDOW_HEIGHT);
  }

  public MainWindow(int windowWidth, int windowHeight) {
    super("Tank1991");
    setInitialParameters(windowWidth, windowHeight);
    setUpPanels(windowWidth, windowHeight);
    setVisible(true);
  }

  public JPanel getLevelPanel() {
    return levelPanel;
  }

  public int getCurrentLevelNumber() {
    JLabel label = (JLabel) scorePanel.getComponent(1);
    return Integer.parseInt(label.getText());
  }

  public void setCurrentLevelNumber(int currentLevelNumber) {
    JLabel label = (JLabel) scorePanel.getComponent(1);
    label.setText(String.valueOf(currentLevelNumber));
  }

  private void setInitialParameters(int windowWidth, int windowHeight) {
    setLayout(new BorderLayout());
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(windowWidth, windowHeight);
    setBackground(Color.white);
    setIgnoreRepaint(true);
  }

  private void setUpPanels(int windowWidth, int windowHeight) {
    initScorePanel(windowWidth, windowHeight);
    initLevelPanel(windowWidth, windowHeight);
    getContentPane().add(levelPanel, BorderLayout.CENTER);
    getContentPane().add(scorePanel, BorderLayout.PAGE_END);
  }

  private void initScorePanel(int windowWidth, int windowHeight) {
    JLabel label = new JLabel("This game is gonna be a lot of fun!");
    JLabel levelNumber = new JLabel("0");
    scorePanel.setSize(windowWidth, 20);
    scorePanel.add(label);
    scorePanel.add(levelNumber);
  }

  private void initLevelPanel(int windowWidth, int windowHeight) {
    levelPanel.setSize(windowWidth, windowHeight - 20);
    levelPanel.setBackground(Color.cyan);
    levelPanel.setOpaque(true);
  }
}
