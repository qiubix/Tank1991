/**
 * Drawable.java
 * @author Kari
 */


package graphics;

import java.awt.Graphics2D;

/**
 * Interfejs sluzacy do wyswietlania na ekranie roznych kontekstow graficznych
 * @author Kari
 */
public interface Drawable {

    /**
     * Odrysowanie kontekstu graficznego na ekranie.
     * @param g dany kontekst graficzny
     * @param width szerokosc ekranu [piksele]
     * @param height wysokosc ekranu [piksele]
     */
    public void draw(Graphics2D g, int width, int height);
    
}
