/**
 * View.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tank1991;

import event.Keyboard;
import graphics.Drawable;
import graphics.ScreenManager;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import level.Level;
import menu.GraphicCounter;
import menu.MainMenu;
import menu.PauseMenu;
import menu.ScorePanel;
import tank1991.Model.GameState;
import util.Observed;
import util.Observer;




public class View implements Observer{
    
    /**
     * Rozmiar czcionki
     */
    private static final int FONT_SIZE = 20;
    
    /**
     * Zestaw mozliwych trybow wyswietlania
     */
    private static final DisplayMode[] POSSIBLE_MODES = {
        new DisplayMode(1366, 768, 32, 0),
        new DisplayMode(1280, 1024, 32,0),
        new DisplayMode(1280, 720, 32, 0),
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(1280, 1024, 32, 0),
        new DisplayMode(1280, 800, 32, 0),
        new DisplayMode(1650, 1050, 32, 0),
        new DisplayMode(1920, 1080, 32, 0)
    };
    
    
    
    /**
     * Przechowuje aktualny kontekst graficzny ekranu
     */
    private Drawable graphicsContext;
    
    /**
     * Panel zawierajacy wszystkie konteksty graficzne
     */
    private JPanel contextPanel;
    
    
    /**
     * Menu glowne
     */
    private MainMenu mainMenu;
    
    
    /**
     * Menu pauzy
     */
    private PauseMenu pauseMenu;
    
    
    /**
     * Panel wynikow
     */
    private ScorePanel scorePanel;
    
    /**
     * Zarzadca ekranu
     */
    protected ScreenManager screen;
    
    /**
     * Okno gry
     */
    protected JFrame window;
    
    /**
     * Model
     */
    protected Model model;
    
    
    
    /**
     * Niewidoczny kursor
     */
    public static final Cursor INVISIBLE_CURSOR = 
            Toolkit.getDefaultToolkit().createCustomCursor( Toolkit.getDefaultToolkit().getImage(""), 
                                                            new Point(0,0),
                                                            "invisible");
    
    
//    private InputManager inputManager;
    
    
    
    public View(Model model){
        this.model = model;
        
        //Inicjalizacja ekranu
        screen = new ScreenManager();
//        DisplayMode displayMode = screen.getCurrentDisplayMode();
        DisplayMode displayMode = screen.getFirstCompatibleMode(POSSIBLE_MODES);
        screen.setFullScreen(displayMode);
        
        //Inicjalizacja okna
        window = screen.getFullScreenWindow();
        window.setFont(new Font("Dialog",Font.PLAIN,FONT_SIZE));
        window.setTitle("Tank 1991");
        window.setBackground(Color.white);
        window.setForeground(Color.LIGHT_GRAY);
        window.setCursor(INVISIBLE_CURSOR);
        window.setFocusTraversalKeysEnabled(false);
        
        
        mainMenu = new MainMenu("main_menu_back_2.jpg", 350, 250);
        pauseMenu = new PauseMenu("frame3.png", "Paused");
        
        scorePanel = new ScorePanel(new GraphicCounter(model.levelCounter, "level_icon3.png"), 
                                    new GraphicCounter(model.lifesCounter, "lifes_icon2.png"), 
                                    new GraphicCounter(model.enemiesCounter, "enemy_icon2.png"), 
                                    new GraphicCounter(model.pointCounter, "level_icon4.png"),
                                    new Point(50,30));
                                    
                                    
        
        contextPanel = new JPanel(new CardLayout());
        contextPanel.add(mainMenu);
        contextPanel.add(pauseMenu);
        contextPanel.add(scorePanel);
        contextPanel.add(model.getLevel());
        window.add(contextPanel);
        
        
        model.setScreenWidth(screen.getWidth());
        model.setScreenHeight(screen.getHeight());
        
        //rozpoczecie w menu glownym
        toggleContext(mainMenu);
    }
    
    
    
    public ScreenManager getScreen(){
        return screen;
    }
    
    
    public MainMenu getMainMenu(){
        return mainMenu;
    }
    
    public PauseMenu getPauseMenu(){
        return pauseMenu;
    }
    

    @Override
    public void signal(Observed observed) {
        draw();
        if (!model.isPaused()) {
            if (model.getGameState() == GameState.LOSS /*&& model.getLevel().getPlayer().isDead()*/) {
                toggleContext(getSummaryMenu("Game over!", true));
            } else if (model.getGameState() == GameState.WIN) {
                toggleContext(getSummaryMenu("Game finished!", true));
            } else if (model.getGameState() == GameState.LEVEL_UP) {
                toggleContext(getSummaryMenu("Level finished!", false));
            } else if (model.getLevel().getPlayer().isDead()) {
                toggleContext(getSummaryMenu("You are dead!", false));
            }
        }
    }

    
    
    /**
     * Odrysowanie obiektow graficznych na ekranie
     */
    public void draw(){
        //Rysowanie
        Graphics2D g = screen.getGraphics();
        g.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, 
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        //Odrysowanie na ekranie aktualnego kontekstu graficznego
        graphicsContext.draw(g, screen.getWidth(), screen.getHeight());
        if(graphicsContext instanceof Level){
            scorePanel.paintComponent(g);
        }
        g.dispose();
        screen.update();
    }

    
    
    /**
     * Przelaczenie kontenkstu graficznego na podany. Wyswietlenie go oraz
     * ustawienie fokusu
     * @param component kontekst graficzny, na ktory chcemy przelaczyc
     */
    public final void toggleContext(JPanel component) {
        component.setVisible(true);
        graphicsContext = (Drawable) component;
        
        if(component instanceof Level){
            window.requestFocus();
            model.setPaused(false);
        }
        else{
            component.requestFocus();
            model.setPaused(true);
            
        }
        
        window.validate();
    }

    
    
    void addMainMenuListener(ActionListener listener){
        mainMenu.addActionListener(listener);
    }


    void addKeyListener(Keyboard keyboard){
        window.addKeyListener(keyboard);
    }
    
    
    /**
     * Funkcja tworzy menu z komunikatem podsumowania rozgrywki.
     *
     * @param greeting tresc komunikatu
     */
    public PauseMenu getSummaryMenu(String greeting, boolean showPoints) {
        PauseMenu result = null;
        if (showPoints) {
            result = new PauseMenu("frame3.png", greeting,
                    "Your score: " + model.pointCounter.get());
        } else {
            result = new PauseMenu("frame3.png", greeting);
        }
        return result;
    }
}
