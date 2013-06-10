/**
 * GraphicCounter.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package menu;

import graphics.ImageLoader;
import java.awt.Image;
import util.Counter;


public class GraphicCounter {

    protected Image icon;
    
    protected Counter counter;
    
    public GraphicCounter(Counter counter, String filename){
        this.counter = counter;
        this.icon = ImageLoader.loadImage(filename);
    }
    
    public Image getIcon(){
        return icon;
    }
    
    public void setIcon(Image icon){
        this.icon = icon;
    }
    
    public int get(){
        return counter.get();
    }
    
}
