package core;

import objects.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

  private Model model;

  private View view;

  public Controller(Model model, View view) {
    this.model = model;
    this.view = view;

    initKeyBindings();
  }

  private void initKeyBindings() {
    JComponent activeWindow = view.getLevelPanel();
    activeWindow.getInputMap().put(KeyStroke.getKeyStroke("UP"), "MOVE_UP");
    activeWindow.getActionMap().put("MOVE_UP", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Player player = model.getPlayer();
        player.moveUp();
        System.out.println("Player moving up");
      }
    });
    activeWindow.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "MOVE_DOWN");
    activeWindow.getActionMap().put("MOVE_DOWN", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Player player = model.getPlayer();
        player.moveDown();
        System.out.println("Player moving down");
      }
    });
    activeWindow.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "MOVE_RIGHT");
    activeWindow.getActionMap().put("MOVE_RIGHT", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Player player = model.getPlayer();
        player.moveRight();
        System.out.println("Player moving right");
      }
    });
    activeWindow.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "MOVE_LEFT");
    activeWindow.getActionMap().put("MOVE_LEFT", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Player player = model.getPlayer();
        player.moveLeft();
        System.out.println("Player moving left");
      }
    });
    activeWindow.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "EXIT");
    activeWindow.getActionMap().put("EXIT", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.finishGame();
      }
    });
  }

  @Override
  public void update(Observable o, Object arg) {

  }
}
