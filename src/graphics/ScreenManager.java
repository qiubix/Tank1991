/**
 * ScreenManager.java
 * @author Kari
 * Zarzadca ekranow.
 * Posiada mozliwosc przelaczana na tryb pelnoekranowy, zwraca kontekst graficzny ekranu,
 * posiada podwojne buforowanie i przelaczanie stron (tworzenie BufferStrategy)
 */



package graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Zarzadca ekranow. Zapewnia podwojne buforowanie i przelaczanie stron.
 * @author Kari
 */
public class ScreenManager {
    //Urzadzenie graficzne 
    private GraphicsDevice device;
            
    protected final JFrame window = new JFrame();
    
    /**
     * Tworzenie nowego obiektu ScreenManager. 
     * Inicjalizacja urzadzenia poprzez pobranie lokalnych ustawien srodowiska graficznego
     */
    public ScreenManager(){
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = environment.getDefaultScreenDevice();
    }
    
    
    /**
     * 
     * @return lista trybow graficznych urzadzenia
     */
    public DisplayMode[] getCompatibleDisplayModes(){
        return device.getDisplayModes();
    }
    
    
    /**
     * Zwraca pierwszy kompatybilny tryb graficzny
     * @param modes - tablica trybow graficznych
     * @return kompatybilny tryb graficzny lub null, jesli nie znalazl
     */
    public DisplayMode getFirstCompatibleMode(DisplayMode[] modes){
        DisplayMode[] goodModes = device.getDisplayModes();
        for(int i=0; i < modes.length; i++){
            for(int j=0; j < goodModes.length; j++){
                if(displayModesMatch(modes[i], goodModes[j]))
                    return modes[i];
            }
        }
        return null;
    }
    
    
    /**
     * Pobranie aktualnego trybu graficznego
     * @return aktualny tryb graficzny
     */
    public DisplayMode getCurrentDisplayMode(){
        return device.getDisplayMode();
    }
    
    
    /**
     * Porownanie dwoch trybow graficznych
     * @param mode1 pierwszy tryb
     * @param mode2 drugi tryb
     * @return true gdy identyczne
     */
    public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2){
        if(mode1.getWidth() != mode2.getWidth() || mode1.getHeight() != mode2.getHeight())
            return false;
        
        if(mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
           mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
           mode1.getBitDepth() != mode2.getBitDepth())
            return false;
        
        if(mode1.getRefreshRate() != mode2.getRefreshRate() &&
           mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
           mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN)
            return false;
        
        return true;
    }
    
    
    /**
     * Przechodzi w tryb pelnoekranowy i zmienia tryb graficzny
     * W przypadku podaniu nulla lub jesli system nie obsluguje zmiany trybow, 
     * wykorzystuje biezacy tryb graficzny
     * <p>
     * Wykorzystywany jest obiekt BufferStrategy z dwoma buforami
     * <p>
     * Korzystam z aktywnego renderowania, wiec JFrame wykorzystywane jako okno pelnoekranowe 
     * nie musi odbierac zdarzenia paint z systemu operacyjnego
     * @param displayMode tryb graficzny, na ktory chcemy zmienic
     */
    public void setFullScreen(DisplayMode displayMode){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //domyslna operacja zamykania
        
        window.setUndecorated(true);                             //wylaczony pasek i obramowanie
        
        window.setIgnoreRepaint(true);                           //paint SO niepotrzebny
        window.setResizable(false);                              //nie mozna zmieniac rozmiaru
        
        device.setFullScreenWindow(window);                      //przelaczenie na pelny ekran
        
        if(displayMode != null && device.isDisplayChangeSupported()){
            try{
                device.setDisplayMode(displayMode);
            }
            catch(IllegalArgumentException ex){
                ex.printStackTrace(System.out);
            }
            window.setSize(displayMode.getWidth(), displayMode.getHeight());
        }
        window.createBufferStrategy(2);
    }
    
    
    
    /**
     * Zwraca kontekst graficzny ekranu
     * @return 
     */
    public Graphics2D getGraphics(){
        if(window != null){
            BufferStrategy strategy = window.getBufferStrategy();
            return (Graphics2D)strategy.getDrawGraphics();
        }
        else{
            return null;
        }
    }
    
    
    
    /**
     * Odrysowuje ekran. ScreenManager korzysta z podwojnego buforowania, wiec wykorzystujace
     * go aplikacje musza wywolywac update() w celu pokazania narysowanej grafiki. 
     * <p>
     * Aplikacja musi takze usuwac obiekt graficzny.
     */
    public void update(){
        if(window != null){
            BufferStrategy strategy = window.getBufferStrategy();
            if(!strategy.contentsLost())
                strategy.show();
        }
        Toolkit.getDefaultToolkit().sync();     //operacja sync() dziala w niektorych systemach. M.in w Linux naprawia problem z kolejka zdarzen
    }
    
    
    /**
     * Zwraca okno uzywane w trybie pelnoekranowym
     * @return null gdy urzadzenie nie jest w trybie pelnoekranowym. 
     */
    public JFrame getFullScreenWindow(){
        return window;
    }
    
    
    /**
     * Zwraca szerokosc okna pelnoekranowego
     * @return szerokosc okna<p>
     * @return null gdy nie jest w trybie pelnoekranowym
     */
    public int getWidth(){
        if(window != null){
            return window.getWidth();
        }
        else{
            return 0;
        }
    }
    
    
    
    /**
     * Zwraca wysokosc okna pelnoekranowego
     * @return wysokosc okna<p>
     * @return null gdy nie jest w trybie pelnoekranowym
     */
    public int getHeight(){
        if(window != null){
            return window.getHeight();
        }
        else{
            return 0;
        }
    }
    
    
    
    /**
     * Czysci okno i wychodzi z trybu pelnoekranowego
     */
    public void restoreScreen(){
        if(window != null){
            window.dispose();
        }
        try {
        	device.setFullScreenWindow(null);
        }
        catch (Exception ex) {
        	ex.printStackTrace(System.err);
        }
    }
    
    
    
    /**
     * Tworzy obiekt Image zgodny z biezacym ekranem
     * @param w szerokosc
     * @param h wysokosc
     * @param transparency przezroczystosc
     * @return obiekt typu Image przechowujacy dane na temat biezacych ustawien ekranu
     */
    public BufferedImage createCompatibleImage(int w, int h, int transparency){
        if(window != null){
            GraphicsConfiguration gc = window.getGraphicsConfiguration();
            return gc.createCompatibleImage(h, h, transparency);
        }
        else{
            return null;
        }
    }
}
