/**
 * MainMenu.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package menu;

import graphics.ImageLoader;
import graphics.NullRepaintManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;



public class MainMenu extends Menu{
    
    /**
     * Przyciski w menu glownym
     */
    private JButton resumeGameButton,
                    newGameButton,
                    mapKeysButton,
                    creditsButton,
                    exitButton;
    
    /**
     * Okresla, czy rozgrywka zostala rozpoczeta
     */
    private boolean isStarted;
    
    
    
    /**
     * Tworzenie obiektu menu glownego
     * @param filename nazwa pliku tla
     * @param x wspolrzedna pozioma pozycji menu
     * @param y wspolrzedna pionowa pozycji menu
     */
    public MainMenu(String filename, int x, int y){
        super(filename);
        configureInput();
        isStarted = false;
        Dimension dimension = new Dimension(x,y);
        NullRepaintManager.install();
        
        //ustawienie ukladu i pozycjonowania menu
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(dimension));
        
        //wyprodukowanie przyciskow
        ButtonFactory factory = new ButtonFactory();
        resumeGameButton = factory.createButton("Resume game", "resume", ImageLoader.loadIcon("main_menu_button_5.png"));
        newGameButton = factory.createButton("New game", "new", ImageLoader.loadIcon("main_menu_button_1.png"));
        mapKeysButton = factory.createButton("Controls   ", "map_keys", ImageLoader.loadIcon("main_menu_button_2.png"));
        creditsButton = factory.createButton("Credits     ", "credits", ImageLoader.loadIcon("main_menu_button_3.png"));
        exitButton = factory.createButton("Exit game", "exit", ImageLoader.loadIcon("main_menu_button_4.png"));
        
        //Dodanie przyciskow do menu:
        add(resumeGameButton);
        add(newGameButton);
        add(mapKeysButton);
        add(creditsButton);
        add(exitButton);
    }
    
    
    
    /**
     * Przeniesienie fokusu na menu i aktywacja przyciskow
     */
    @Override
    public void requestFocus(){
        resumeGameButton.setVisible(isStarted);
        if(isStarted){
            resumeGameButton.requestFocus();
        }
        else{
            newGameButton.requestFocus();
        }
    }
    
    
    
    /**
     * Ustawienie statusu rozpoczecia gry
     * @param isStarted status rozpoczecia gry
     */
    public void setIsStarted(boolean isStarted){
        this.isStarted = isStarted;
    }
    
    
    /**
     * 
     * @param g
     * @param screenWidth
     * @param screenHeight 
     */
    @Override
    public void draw(Graphics2D g, int screenWidth, int screenHeight) {
        //rysowanie tla
        g.drawImage(background, 0, 0, background.getWidth(null), background.getHeight(null),null);
        
        //rysowanie przyciskow
        paintComponents(g);
    }

    

    
    /**
     * Konfigurowanie poruszania sie po menu
     */
    private void configureInput() {
        Set forwardKeys = new HashSet();
        Set backwardKeys = new HashSet();
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                forwardKeys);
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
                backwardKeys);
        UIManager.put("Button.defaultButtonFollowsFocus", true);
    }

    
    /**
     * Dodanie przechwytywacza zdarzen dla przyciskow
     *
     * @param listener obiekt obslugujacy zdarzenia generowane przez przyciski
     */
    public void addActionListener(ActionListener listener) {
        // aktywacja nasluchiwania
        resumeGameButton.addActionListener(listener);
        newGameButton.addActionListener(listener);
        mapKeysButton.addActionListener(listener);
        creditsButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
}