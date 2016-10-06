package graphics;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {

  public static Image loadImage(String filename) {
    return new ImageIcon(ImageLoader.class.getResource(filename)).getImage();
  }
}
