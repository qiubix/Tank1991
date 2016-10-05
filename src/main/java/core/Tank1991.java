package core;

import utils.Timer;

public class Tank1991 {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("My own Tank game!");
    Model model = new Model();
    View view = new View(model);
    model.addObserver(view);

    Timer timer = new Timer();
    while (true) {
      model.update(timer.tick());
      Thread.sleep(20);
    }
  }
}
