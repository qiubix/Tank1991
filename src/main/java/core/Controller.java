package core;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

  private Model model;

  public Controller(Model model) {
    this.model = model;
  }

  @Override
  public void update(Observable o, Object arg) {

  }
}
