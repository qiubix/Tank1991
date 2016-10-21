package level;

import graphics.Drawable;
import objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level extends JPanel implements Drawable //implements Drawable
{

  private Player player;

  public Level() {
    setDoubleBuffered(true);
    player = (Player) Player.create();
    player.setPositionX(20);
    player.setPositionY(20);
//    player.setVelocityX(1);
  }

  public Level(int levelWidth, int levelHeight) {
    setDoubleBuffered(true);
    setSize(levelWidth, levelHeight);
    player = (Player) Player.create();
    player.setPositionX(20);
    player.setPositionY(20);
  }

  public Player getPlayer() {
    return player;
  }

  @Override
  public void draw(Graphics2D graphics2D) {
    paintComponent(graphics2D);
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
//    graphics.setBackground(Color.cyan);
    drawPlayer(graphics);
  }

  private void drawPlayer(Graphics graphics) {
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), null);
  }

//  @Override
//  public void actionPerformed(ActionEvent e) {
//    repaint();
//  }
}
