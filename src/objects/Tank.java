/**
 * Tank.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.Animation;
import graphics.ImageLoader;
import graphics.Reshape.Flip;
import graphics.Reshape.Identity;
import graphics.Reshape.Mirror;
import graphics.Reshape.Reverse;
import graphics.Reshape.Transformation;
import java.awt.Image;
import java.util.Arrays;
import java.util.List;


public class Tank extends DynamicObject{
    
    /** predkosc czolgu */
    private static final float SPEED = .1f;
    
    /** Lista mozliwych transformacji czoglu */
    public static final List<Transformation> transformations;
    static{
        transformations = Arrays.asList(new Identity(), new Mirror(), new Flip(), new Reverse());
    }
    
    /** Mozliwe stany czolgu (ALIVE, DYING, DEAD) */
    public enum State{
        ALIVE, DYING, DEAD;
    }
    
    /** Mozliwe kierunku czoglu */
    public enum Direction{
        LEFT, RIGHT, UP, DOWN
    }
    
    /** Animacje czolgu */
    protected Animation movingLeft,
                        movingRight,
                        movingUp,
                        movingDown;
    
    
    /** Przechowuje informacje o aktualnym stanie obiektu     */
    protected State state;
    
    
    /** Kazdy czolg ma swoja kule, ktora moze strzelac     */
    protected Bullet bullet;
    
    /** Przechowuje informacje o aktualnym kierunku, w ktorym zwrocony jest czolg     */
    protected Direction direction;
    
    /** Czas, ktory obiekt jest w danym stanie     */
    protected long stateTime;
    
    
    /** Okresla, czy obiekt jest podczas skretu. Potrzebne do wyrownania przy skrecie     */
    protected boolean turning;
    
    
    /** Okresla, czy obiekt strzela     */
    protected boolean shooting;
    
    
    /**
     * Konstruktor obiektu klasy Tank. 
     * Laduje liste animacji, ustawia status na ALIVE i poczatkowa animacje
     * @param animationList Lista animacji, ktore sa uzywane przez dany obiekt. 
     * Kolejne z nich sa wyswietlane odpowiednio przy poruszaniu w lewo, prawo, do gory i do dolu
     * 
     */
    public Tank(List<Animation> animationList){
        super(animationList.get(2));
        movingLeft = animationList.get(0);
        movingRight = animationList.get(1);
        movingUp = animationList.get(2);
        movingDown = animationList.get(3);
        
        state = State.ALIVE;
        direction = Direction.DOWN;
        turning = false;
        shooting = false;
        List<Image> bulletFrames = ImageLoader.loadImageList("bullet_", "png");
        bullet = new Bullet(ImageLoader.createAnimation(bulletFrames, 100, new Identity()), this);
    }
    
    
    /**
     * Sprawdza, czy czolg zostal zabity
     * @return 
     */
    public boolean isDead(){
        return state == State.DEAD;
    }
    
    /**
     * Zwraca kule czolgu
     * @return kula danego czolgu (odnosnik do obiektu typu Bullet)
     */
    public Bullet getBullet(){
        return bullet;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Gettery i settery stanu i kierunku obiektu. Typy wyliczeniowe State i Direction">
    /**
     * Zwraca stan obiektu
     * @return ALIVE/DYING/DEAD
     */
    public State getState(){
        return state;
    }
    
    
    /**
     * Ustawia stan obiektu
     * @param state nowy stan obiektu
     */
    public void setState(State state){
        if(this.state != state){
            this.state = state;
        }
    }
    
    /**
     * Zwraca kierunek, w ktorym jest zwrocony czolg
     * @return Direction ( LEFT, RIGHT, UP, DOWN ) 
     */
    public Direction getDirection(){
        return direction;
    }
    
    public float getSpeed(){
        return SPEED;
    }//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Kierunek, skrecanie i strzelanie">
    /**
     * Ustawia flage kierunku, w ktory jest zwrocony czolg
     * @param direction 
     */
    public void setDirection(Direction direction){
        if(this.direction != direction){
            this.direction = direction;
        }
    }
    
    /**
     * Sprawdza, czy czolg jest w fazie skretu
     * @return 
     */
    public boolean isTurning(){
        return turning;
    }
    
    /**
     * Sprawdza, czy czolg wykonal skret. 
     * @param newDirection nowy kierunek, w ktory zwrocil sie czolg
     */
    public void checkIfTurned(Direction newDirection){
        if(direction != newDirection){
            turning = true;
        }
    }
    
    /**
     * Ustawia flage okreslajaca, czy czolg jest w fazie skretu na false
     */
    private void resetTurn(){
        turning = false;
    }
    
    /**
     * Sprawdzenie, czy czolg strzela
     * @return true jesli strzela
     */
    public boolean isShooting(){
        return shooting;
    }
    
    public static int shootingCount =0;
    
    /**
     * Ustawienie flagi okreslajacej, czy czolg strzela
     * @param value true jesli czogl strzela
     */
    public void tankShoots(boolean value){
        //System.out.println(++shootingCount);
        shooting = value;
    }//</editor-fold>
    
    

    /**
     * Aktualizacja kierunku i animacji czolgu
     * @param elapsedTime czas od ostatniej aktualizacji
     */
    @Override
    public void update(long elapsedTime){
        Animation animation = (Animation) graphic;
        Direction oldDirection = direction;
        resetTurn();
        if(getVelocityX() < 0){
            animation = movingLeft;
            direction = Direction.LEFT;
        }
        else if(getVelocityX() > 0){
            animation = movingRight;
            direction = Direction.RIGHT;
        }
        else if(getVelocityY() < 0){
            animation = movingUp;
            direction = Direction.UP;
        }
        else if(getVelocityY() > 0){
            animation = movingDown;
            direction = Direction.DOWN;
        }
        if( animation != graphic ){
            graphic = animation;
            //turning = true;
            checkIfTurned(oldDirection);
            animation.start();
        }
        else{
            graphic.update(elapsedTime);
        }
        
    }
    
    
    /**
     * Reakcja na zderzenie w poziomie
     * @see objects.Player
     * @see objects.Enemy
     */
    @Override
    public void reactToHorizontalCollision(){
        setVelocityX(-getVelocityX());
    }
    
    /**
     * Reakcja na zderzenie w pionie
     * @see objects.Player
     * @see objects.Enemy
     */
    @Override
    public void reactToVerticalCollision(){
        setVelocityY(-getVelocityY());
    }
    
    
    @Override
    public void activate(){
        //setVelocityY(SPEED);
    }
    
}
