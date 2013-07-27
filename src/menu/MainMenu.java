package menu;

import graphics.ImageLoader;
import graphics.NullRepaintManager;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

//TODO: change constructor and create some constants to keep file names
public class MainMenu extends Menu{

    private JButton resumeGameButton,
                    newGameButton,
                    mapKeysButton,
                    creditsButton,
                    exitButton;

    private boolean isGameStarted;

    private static String backgroundFileName = "main_menu_back_1.jpg";

    /**
     * Tworzenie obiektu menu glownego
     * @param dimension rozmiar pustej przestrzeni nad przyciskami
     */
    public MainMenu(Dimension dimension){
        super(backgroundFileName);
        isGameStarted = false;

        configureInput();
        NullRepaintManager.install();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        produceMenuButtons();
        addMenuButtons(dimension);
    }

    /**
     * Konfigurowanie poruszania sie po menu
     */
    private void configureInput() {
        Set forwardKeys = new HashSet();
        Set backwardKeys = new HashSet();
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);
        UIManager.put("Button.defaultButtonFollowsFocus", true);
    }

    private void produceMenuButtons() {
        ButtonFactory factory = new ButtonFactory();
        resumeGameButton = factory.createButton("Resume game", "resume", ImageLoader.loadIcon("main_menu_button_5.png"));
        newGameButton = factory.createButton("New game", "new", ImageLoader.loadIcon("main_menu_button_1.png"));
        mapKeysButton = factory.createButton("Controls   ", "map_keys", ImageLoader.loadIcon("main_menu_button_2.png"));
        creditsButton = factory.createButton("Credits     ", "credits", ImageLoader.loadIcon("main_menu_button_3.png"));
        exitButton = factory.createButton("Exit game", "exit", ImageLoader.loadIcon("main_menu_button_4.png"));
    }

    private void addMenuButtons(Dimension dimension) {
        add(Box.createRigidArea(dimension));
        addButton(resumeGameButton);
        addButton(newGameButton);
        addButton(mapKeysButton);
        addButton(creditsButton);
        addButton(exitButton);
    }

    private void addButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(button);
    }

    /**
     * Przeniesienie fokusu na menu i aktywacja przyciskow
     */
    @Override
    public void requestFocus(){
        resumeGameButton.setVisible(isGameStarted);
        if(isGameStarted){
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
        this.isGameStarted = isStarted;
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
