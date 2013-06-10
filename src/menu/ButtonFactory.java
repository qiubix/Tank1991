/**
 * ButtonFactory.java
 * @author Kari
 */

package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JButton;



/**
 * Produkcja przyciskow menu
 */
public class ButtonFactory {
    
    private static final Font BIG_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 36);
    
    
    /**
     * Domyslny rozmiar dla przycisku
     */
    private Dimension buttonDimension = new Dimension(400, 100);
    
    public JButton createButton(String description, String action, Icon icon){
        JButton button = new JButton();
        
        //Ustawienie czcionki
        button.setFont(BIG_FONT);
        button.setForeground(Color.white);
        
        //Ustawienie napisu i ikony
        button.setText(description);
        button.setIcon(icon);
        
        //ustawienie rozmiaru
        button.setMaximumSize(buttonDimension);
        
        //dodanie fokusu
        button.setFocusable(true);
        
        //ustawienie nazwy akcji
        button.setActionCommand(action);
        
        //usuniecie tla i obramowania
        button.setBorder(null);
        button.setContentAreaFilled(false);
        button.setIgnoreRepaint(true);
        
        return button;
    }
    
    
    
    /**
     * Ustawienie nowego domyslnego rozmiaru produkowanych buttonow
     * @param dimension nowy rozmiar
     */
    public void setButtonDimension(Dimension dimension){
        buttonDimension = dimension;
    }
    
}
