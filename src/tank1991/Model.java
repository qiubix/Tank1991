/**
 * Model.java
 * @author Kari
 */



package tank1991;

import java.util.Iterator;
import level.Level;
import level.LevelLoader;
import objects.DynamicObject;
import objects.Eagle;
import objects.Enemy;
import objects.Tank;
import util.Counter;
import util.Observed;

/**
 * Model we wzorcu projektowym MVC
 * Klasa sluzy do przechowywania informacji o obiektach gry, planszy i silniku
 * fizycznym rozgrywki
 * @author Kari
 */
public class Model extends Observed{

    private static final int LIVES = 3;
    private static int ENEMIES_NUMBER = 10; 
    private boolean isPaused;
    
    /** Przechowuje aktualny poziom     */
    private Level map;    
    
    /** Zarzadca poziomow gry. Pozwala wczytywac nowe poziomy i nimi zarzadza    */
    private LevelLoader levelManager;
    
    /** Obsluga fizyki gry - m.in. zderzenia obiektow     */
    private GamePhysics physics;
    
    /** Stan rozgrywki     */
    private GameState gameState;
    
    /** Mozliwe stany gry     */
    public enum GameState{
        PLAY, LOSS, WIN, LEVEL_UP
    }
    
    /** licznik zyc bohatera */
    public Counter lifesCounter;
    
    /** licznik wrogow, ktorych nalezy pokonac, alby przejsc na nastepny poziom */
    public Counter enemiesCounter;
    
    /** Licznik poziomow */
    public Counter levelCounter;
    
    /** Licznik punktow gracza */
    public Counter pointCounter;
    
    /** Szerokosc ekranu w pikselach */
    private int screenWidth;
    /** Wysokosc ekranu w pikselach */
    private int screenHeight;
    

    public Model(){
        levelManager = new LevelLoader();
        physics = new GamePhysics();
        levelCounter = new Counter();
        lifesCounter = new Counter();
        enemiesCounter = new Counter();
        pointCounter = new Counter();
        
        setPaused(true);
        startGame();
    }
    
    /**
     * Reset licznikow i poziomu, potrzebny przy kazdym rozpoczeciu gry
     */
    public final void startGame(){
        setGameState(gameState.PLAY);
        levelManager.reset();
        loadNextLevel();
        levelCounter.set(levelManager.getLevelNumber());
        lifesCounter.set(LIVES);
        enemiesCounter.set(ENEMIES_NUMBER);
    }
    
    /**
     * Ustawia liczbe wrogow, ktorych trzeba pokonac, zeby ukonczyc poziom
     * @param count nowa liczba wrogow
     */
    public static void setEnemiesCount(int count){
        ENEMIES_NUMBER = count;
    }
    
    /**
     * Pobieranie szerokosci ekranu
     * @return szerokosc ekranu w pikselach
     */
    public int getScreenWidth(){
        return screenWidth;
    }
    
    /**
     * Pobieranie wysokosci ekranu
     * @return wysokosc ekranu w pikselach 
     */
    public int getScreenHeight(){
        return screenHeight;
    }
    
    /**
     * Ustawianie szerokosci ekranu (zmiennej przechowujacej ta szerokosc)
     * @param screenWidth ustawia zmienna screenWidth
     */
    public void setScreenWidth(int screenWidth){
        this.screenWidth = screenWidth;
    }
    
    /**
     * Ustawienie zmiennej przechowujacej wysokosc ekranu
     * @param screenHeight nowa wysokosc ekranu
     */
    public void setScreenHeight(int screenHeight){
        this.screenHeight = screenHeight;
    }
    
    /**
     * Informacja o stanie pauzy
     * @return true jesli gra jest zatrzymana
     */
    public boolean isPaused(){
        return isPaused;
    }
    
    /**
     * Przelaczenie trybu pauzy
     * @param paused true powoduje przelaczenie gry w tryb pauzy
     */
    public final void setPaused(boolean paused){
        this.isPaused = paused;
    }
    
    /**
     * Sprawdzenie stanu gry
     * @return aktualny stan gry ( gra, wygrana/przegrana, nastepny poziom ) 
     */
    public GameState getGameState(){
        return gameState;
    }
    
    /**
     * Ustawienie stanu rozgrywki
     * @param gameState nowy stan rozgrywki
     */
    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }
    
    /**
     * Sprawdzenie konca gry
     * @return true jesli gra sie zakonczyla
     */
    public boolean isGameOver(){
        return gameState == GameState.WIN || gameState == GameState.LOSS;
    }
    
    /**
     * Pobieranie mapy aktualnego poziomu
     * @return 
     */
    public Level getLevel(){
        return map;
    }
    
    /**
     * Funkcja ustawia status nastepnego poziomu (koniec gry/ladowanie kolejnej
     * mapy).
     */
    public void nextLevel() {
        setGameState((levelManager.isNextLevel()) ? gameState.LEVEL_UP : gameState.WIN);
    }
        
    /**
     * Przeladowanie aktualnego levelu
     */
    public void reloadLevel(){
        map = levelManager.reloadLevel();
    }
    
    /**
     * Wczytanie nastepnego poziomu, reset licznika wrogow, ustawienie stanu rozgrywki
     */
    public void loadNextLevel(){
        map = levelManager.loadNextLevel();
        enemiesCounter.set(ENEMIES_NUMBER);
        setGameState(GameState.PLAY);
    }
    
    /**
     * Aktualizacja stanu rozgrywki i poinformowanie o tym obiektow obserwujacych
     * model, na podstawie czasu, jaki minal od ostatniej aktualizacji
     * @param elapsedTime czas, ktory uplynal od ostatniego odswiezenia modelu
     */
    public void update(long elapsedTime){
        int enemiesOnMap = 0;
        if(!isPaused()){
            
            updatePlayerPhysics(elapsedTime);
            
            //aktualizacja fizyki wszystkich obiektow
            for (Iterator<DynamicObject> i = map.iterator(); i.hasNext();) {
                DynamicObject object = i.next();
                physics.update(this, object, elapsedTime);
                
                //jesli aktualizowany obiekt jest wrogiem, usuniecie trafionego czolgu z mapy
                //aktualizacja licznikow pktow
                if (object instanceof Enemy) {
                    enemiesOnMap++;
                    if (((Enemy) object).getState() == Tank.State.DEAD) {
                        i.remove();
                        enemiesCounter.decrement();
                        pointCounter.add(100);
                        if(enemiesCounter.get() == 0){
                            nextLevel();
                        }
                    }
                    if(((Enemy)object).isShooting()){
                        physics.update(this, ((Enemy)object).getBullet(), elapsedTime);
                    }
                }
                else if(object instanceof Eagle){
                }
            }
            if(enemiesCounter.get() > 2 && enemiesOnMap < 3){
                levelManager.addNewEnemy(map, Enemy.class);
            }
            
        }
        signalToObservers();
    }

	private void updatePlayerPhysics(long elapsedTime) {
		//aktualizacja fizyki gracza
		physics.update(this, map.getPlayer(), elapsedTime);
		if(map.getPlayer().isShooting()){
		    physics.update(this, map.getPlayer().getBullet(), elapsedTime);
		}
	}
    
}
