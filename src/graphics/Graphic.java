/**
 * Graphic.java
 * @author Kari
 */


package graphics;

import java.awt.Image;


/**
 * Reprezentacja obiektu graficznego. Dziedzicza po tej klasie Picture i Animation
 * 
 * @see graphics.Animation
 */
public abstract class Graphic {
    
    
    /**
     * Pobierz obrazek
     * @return aktualny obrazek obiektu graficznego
     */
    public abstract Image getImage();
    
    
    public abstract int getWidth();
    
    
    public abstract int getHeight();
    
    
    public abstract void update(long elapsedTime);
    
    
    @Override
    public abstract Object clone();

}
