/**
 * Enemy.java
 * @author Kari
 */



package objects;

import graphics.Animation;
import graphics.ImageLoader;
import graphics.Reshape;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;



/**
 * Klasa reprezentujaca wroga
 * @author Kari
 */
public class Enemy extends Tank{
    
    /** Predkosc wroga */
    private static final float ENEMY_SPEED = 0.1f;
    
    /** Czas miedzy poszczegolnymi zmianami decyzji */
    private long time;
    protected long elapsedTimeSinceDecision;
    protected long elapsedTimeInState;
    
    /** Zarodek liczby losowej */
    private Random random = new Random(13);
    
    /** Decyzje, ktore moga podejmowac wrogie czolgi     */
    public enum Decision{
        TURN_LEFT, TURN_RIGHT, TURN_UP, TURN_DOWN, SHOOT
    }
    
    /** Aktualna decyzja wrogiego czolgu */
    protected Decision decision;
    
    
    /** Stan wroga - aktywny/nieaktywny */
    private boolean active = false;
    
    
    /**
     * Konstruktor klasy wroga. Inicjalizuje timery i decyzje wroga oraz jego animacje
     * @param animationList lista animacji wroga
     */
    public Enemy(List<Animation> animationList){
        super(animationList);
        time = System.currentTimeMillis();
        elapsedTimeInState = 0;
        elapsedTimeSinceDecision = 0;
        decision = Decision.TURN_DOWN;
        shooting = false;
        direction = Direction.DOWN;
    }
    
    
    public static GameObject create(){
        return EnemyFactory.create();
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Fabryka wrogow">
    /**
     * Klasa odpowiadajaca za zaladowanie obrazkow i utworzenie z nich animacji wroga
     * Posiada metode pozwalajaca utworzyc nowy obiekt wroga
     */
    private static class EnemyFactory{
        static final List<Image> enemyMovingUp;
        
        static final List<Image> enemyMovingLeft;
        
        static final List<Reshape.Transformation> transformations;
        
        static final List<Animation> enemyAnimation;
        
        static{
            enemyMovingUp = ImageLoader.loadImageList("enemy", "png");
            enemyMovingLeft = ImageLoader.loadImageList("enemy_left", "png");
            transformations = Arrays.asList(new Reshape.Identity(), new Reshape.Reverse());
            enemyAnimation = new LinkedList<>();
            enemyAnimation.addAll(ImageLoader.createAnimationSet(enemyMovingLeft, 200, transformations));
            enemyAnimation.addAll(ImageLoader.createAnimationSet(enemyMovingUp, 200, transformations));
        }
        
        public static GameObject create(){
        	System.out.println(enemyAnimation.size());
            return new Enemy(enemyAnimation); 
        }
    }//</editor-fold>
    
    
    /**
     * Sklonowanie obiektu wrogiego czolgu
     * @return odnosnik do sklonowanego obiektu
     */
    @Override
    public Object clone(){
        Object object = null;
        Constructor constructor = getClass().getConstructors()[0];
        try{
            object = constructor.newInstance((Object) Arrays.asList(
                    (Animation) movingLeft.clone(),
                    (Animation) movingRight.clone(),
                    (Animation) movingUp.clone(),
                    (Animation) movingDown.clone()
                    ));
        }
        catch(Exception ex){
            ex.printStackTrace(System.out);
            System.out.println("Blad! Nie mozna sklonowac wroga!");
        }
        return object;
    }
    
    /**
     * Pobiera aktualna decyzje wroga
     * @return 
     */
    public Decision getDecision(){
        return decision;
    }
    
    
    /**
     * Pobiera kule wroga
     * @return 
     */
    @Override
    public Bullet getBullet(){
        return bullet;
    }
    
    
    
    /**
     * Aktualizacja wrogiego czolgu. Strzelanie co okreslony czas
     * @param elapsedTime 
     */
    @Override
    public void update(long elapsedTime){
        
        /*elapsedTimeSinceDecision += elapsedTime;
        elapsedTimeInState += elapsedTime;
        
        if(elapsedTimeSinceDecision >= 1000){
            elapsedTimeSinceDecision = 0;
            switch(random.nextInt(6)){
                case 0: decision = Decision.TURN_DOWN; break;
                case 1: decision = Decision.TURN_UP; break;
                case 2: decision = Decision.TURN_LEFT; break;
                case 3: decision = Decision.TURN_RIGHT; break;
                case 4: decision = Decision.SHOOT; break;
                default: break;
            }
        }*/
        
        shoot();
        
        //update czolgu (polozenie, animacja)
        super.update(elapsedTime);
        
    }
    
    
    /**
     * Realizacja strzalu wrogiego czolgu 
     * Ustawienie odpowiedniego kierunku i predkosci kuli
     */
    private void shoot(){
        if (System.currentTimeMillis() - getBullet().getLastShotTime() >= 500) {
                if (!isShooting()) {
                    tankShoots(true);
                    getBullet().setLastShotTime(System.currentTimeMillis());

                    switch (getDirection()) {
                        case DOWN:
                            getBullet().setVelocityY(getBullet().getSpeed());
                            getBullet().setX(getX() + getWidth()/2  - getBullet().getWidth()/2);
                            getBullet().setY(getY() + getHeight() );
                            break;
                        case UP:
                            getBullet().setVelocityY(-getBullet().getSpeed());
                            getBullet().setX(getX()+ getWidth()/2  - getBullet().getWidth()/2);
                            getBullet().setY(getY() - getBullet().getWidth());
                            break;
                        case RIGHT:
                            getBullet().setVelocityX(getBullet().getSpeed());
                            getBullet().setX(getX() + getWidth());
                            getBullet().setY(getY() + getHeight()/2  - getBullet().getHeight()/2);
                            break;
                        case LEFT:
                            getBullet().setVelocityX(-getBullet().getSpeed());
                            getBullet().setX(getX() - getBullet().getWidth());
                            getBullet().setY(getY()+getHeight()/2 - getBullet().getHeight()/2);
                            break;
                    }
                }
            }
    }
    
    
    
    /**
     * Aktywacja czolgu przeciwnika
     */
    @Override
    public void activate() {
        if (!active) {
            active = true;
            setVelocityY(ENEMY_SPEED);
        }
    }
    
    
    /**
     * Reakcja czolgu na zderzenie w poziomie
     */
    @Override
    public void collideHorizontal(){
//        setVelocityX(-getVelocityX());
        switch(random.nextInt(4)){
                case 0: decision = Decision.TURN_DOWN; break;
                case 1: decision = Decision.TURN_UP; break;
                case 2: decision = Decision.TURN_LEFT; break;
                case 3: decision = Decision.TURN_RIGHT; break;
            }
    }
    
    
    /**
     * Reakcja czolgu na zderzenie w pionie
     */
    @Override
    public void collideVertical(){
//        setVelocityY(-getVelocityY());
        switch(random.nextInt(4)){
                case 0: decision = Decision.TURN_DOWN; break;
                case 1: decision = Decision.TURN_UP; break;
                case 2: decision = Decision.TURN_LEFT; break;
                case 3: decision = Decision.TURN_RIGHT; break;
            }
    }
}
