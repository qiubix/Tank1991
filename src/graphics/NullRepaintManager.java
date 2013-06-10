/**
 * NullRepaintManager.java
 * @author Kari
 */



package graphics;

import javax.swing.JComponent;
import javax.swing.RepaintManager;



/**
 * Klasa zarzadzajaca odrysowywaniem. 
 * Przechwytuje zdarzenia odrysowania i je ignoruje, dzieki czemu nie wystepuja
 * konflikty z wlasnym renderowaniem. Nie chcemy, zeby rysowanie bylo realizowane 
 * w watku rozdzielania zdarzen AWT. 
 * @author Kari
 */
public class NullRepaintManager extends RepaintManager{
    
    
    /**
     * Instalacja zarzadcy odrysowywania
     */
    public static void install(){
        RepaintManager nullRepaintManager = new NullRepaintManager();
        nullRepaintManager.setDoubleBufferingEnabled(false);
        RepaintManager.setCurrentManager(nullRepaintManager);
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Przesloniecie metod, ktore chcemy ignorowac">
    @Override
    public void addInvalidComponent(JComponent c){
        //ignorowanie
    }
    
    @Override
    public void addDirtyRegion(JComponent c, int x, int y, int w, int h){
        //ignorowanie
    }
    
    @Override
    public void markCompletelyDirty(JComponent c){
        //ignorowanie
    }
    
    @Override
    public void paintDirtyRegions(){
        //ignorowanie
    }//</editor-fold>

}
