/**
 * Menu.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package menu;

import graphics.Drawable;
import graphics.ImageLoader;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;


public abstract class Menu extends JPanel implements Drawable{
    
    /**
     * Tlo menu
     */
    protected Image background;
    
    /**
     * Stworzenie obiektu Menu
     * @param filename nazwa pliku tla
     */
    public Menu(String filename){
        this.background = ImageLoader.loadImage(filename);
        setLayout(null);
        setOpaque(false);
        setVisible(false);
    }
    
    
    /**
     * Odrysowanie menu na ekranie
     * @param g
     * @param screenWidth
     * @param screenHeight 
     */
    @Override
    public abstract void draw(Graphics2D g, int screenWidth, int screenHeight);

}
