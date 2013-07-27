package tank1991;

import java.util.Iterator;
import level.Level;
import level.LevelLoader;
import objects.*;
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

    private Level map;   /* TODO: Change name */
    private LevelLoader levelManager;   /* TODO: Change name */
    private GamePhysics physics;
    private GameState gameState;
    
    public enum GameState{
        PLAY, LOSS, WIN, LEVEL_UP
    }
    
    public Counter lifesCounter;

    /* TODO: Improve mechanism of counting enemies */
    public Counter enemiesCounter;
    private int enemiesOnMap;

    public Counter levelCounter;
    public Counter pointCounter;
    
    private int screenWidth;
    private int screenHeight;
    

    public Model(){
        initModel();
        startGame();
    }

    private void initModel() {
        levelManager = new LevelLoader();
        physics = new GamePhysics();
        initCounters();
        pauseGame();
    }

    private void initCounters() {
        levelCounter = new Counter();
        lifesCounter = new Counter();
        enemiesCounter = new Counter();
        pointCounter = new Counter();
    }

    public final void startGame(){
        setGameState(gameState.PLAY);
        levelManager.reset();
        loadNextLevel();
        resetCounters();
    }

    private void resetCounters() {
        levelCounter.set(levelManager.getLevelNumber());
        lifesCounter.set(LIVES);
        enemiesCounter.set(ENEMIES_NUMBER);
    }

    public static void setEnemiesCount(int count){
        ENEMIES_NUMBER = count;
    }

    public int getScreenWidth(){
        return screenWidth;
    }

    public int getScreenHeight(){
        return screenHeight;
    }

    public void setScreenWidth(int screenWidth){
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight){
        this.screenHeight = screenHeight;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void pauseGame() {
        isPaused = true;
    }

    public void resumeGame() {
        isPaused = false;
    }

    public GameState getGameState(){
        return gameState;
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }
    

    public boolean isGameOver(){
        return gameState == GameState.WIN || gameState == GameState.LOSS;
    }

    public Level getLevel(){
        return map;
    }

    public Player getPlayer() {
        return map.getPlayer();
    }
    
    /* TODO: Change name */
    public void nextLevel() {
        setGameState((levelManager.isNextLevel()) ? gameState.LEVEL_UP : gameState.WIN);
    }


    public void reloadLevel(){
        map = levelManager.reloadLevel();
    }


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
            
            //updatePlayerPhysics(elapsedTime);
            physics.update(this, map.getPlayer(), elapsedTime);
            if(map.getPlayer().isShooting()){
                physics.update(this, map.getPlayer().getBullet(), elapsedTime);
            }
            
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
            addNewEnemies(enemiesOnMap);
        }
        signalToObservers();
    }

    private void updatePlayerPhysics(long elapsedTime) {
		physics.update(this, map.getPlayer(), elapsedTime);
		if(map.getPlayer().isShooting()){
		    physics.update(this, map.getPlayer().getBullet(), elapsedTime);
		}
	}

    private void addNewEnemies(int enemiesOnMap) {
        if(enemiesCounter.get() > 2 && enemiesOnMap < 3){
            levelManager.addNewEnemy(map, Enemy.class);
        }
    }
}
