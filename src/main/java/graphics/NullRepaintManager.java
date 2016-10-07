package graphics;

import javax.swing.*;

public class NullRepaintManager extends RepaintManager {

  public static void install(){
    RepaintManager nullRepaintManager = new NullRepaintManager();
    nullRepaintManager.setDoubleBufferingEnabled(false);
    RepaintManager.setCurrentManager(nullRepaintManager);
  }

  @Override
  public void addInvalidComponent(JComponent c) {
  }

  @Override
  public void addDirtyRegion(JComponent c, int x, int y, int w, int h){
  }

  @Override
  public void markCompletelyDirty(JComponent c){
  }

  @Override
  public void paintDirtyRegions(){
  }

}
