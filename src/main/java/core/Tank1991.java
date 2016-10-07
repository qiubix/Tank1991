package core;

import utils.Timer;

import java.awt.EventQueue;

public class Tank1991 {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("My own Tank game!");
    Model model = new Model();
    View view = new View(model);
    Controller controller = new Controller(model, view);
    view.getMainWindow().setVisible(true);
    model.addObserver(view);
    model.addObserver(controller);

    Timer timer = new Timer();
    model.startGame();
    while (model.isRunning()) {
      model.update(timer.tick());
      Thread.sleep(20);
    }

//    EventQueue.invokeLater(new Runnable() {
//      @Override
//      public void run() {
//        Model model = new Model();
//        View view = new View(model);
//        view.getMainWindow().setVisible(true);
//      }
//    });
  }
}
