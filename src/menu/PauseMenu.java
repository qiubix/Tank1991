/**
 * PauseMenu.java
 *
 * @author Kari
 */
package menu;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PauseMenu extends Menu {

    /**
     * Czcionka duzego rozmiaru, szeryfowa, pogrubiona.
     */
    public static final Font bigFont = new Font(Font.SERIF, Font.BOLD, 36);
    
    /**
     * Kontener linii komunikatu.
     */
    protected List<String> labels = new LinkedList<String>();

    
    /**
     * Konstruktor tworzy obiekt ReportMenu.
     *
     * @param filename nazwa pliku tla
     * @param args linie komunikatu
     */
    public PauseMenu(String filename, String... args) {
        super(filename);
        labels.addAll(Arrays.asList(args));
        setFocusable(false);
    }

    
    @Override
    public void draw(Graphics2D g, int screenWidth, int screenHeight) {
        int offset = bigFont.getSize() * 3;
        int x = (screenWidth - background.getWidth(null)) / 2;
        int y = (screenHeight - background.getHeight(null)) / 2;
        g.drawImage(background, x, y, null);
        int labelOffset = (int) (offset * 1.5);
        for (String label : labels) {
            g.drawString(label, x + offset, y + labelOffset);
            labelOffset *= 1.5;
        }
    }
}
