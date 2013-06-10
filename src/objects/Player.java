/**
 * Player.java
 * @author Kari
 */


package objects;

import graphics.Animation;
import graphics.ImageLoader;
import graphics.Reshape.Identity;
import graphics.Reshape.Reverse;
import graphics.Reshape.Transformation;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



/**
 * Klasa reprezentujaca czolg gracza. 
 * Dziedziczy po klasie bazowej czolgu
 * @see objects.Tank
 */
public class Player extends Tank{
    
    /** predkosc gracza */
    private static final float SPEED = .2f;
    
    
    /**
     * Konstruktor gracza
     * ustawia zestaw animacji i poczatkowy kierunek
     * @param animationList lista animacji gracza
     */
    public Player(List<Animation> animationList) {
        super(animationList);
        direction = Direction.UP;
    }
    
    
    /**
     * Pobiera predkosc gracza
     * @return predkosc (typu float)
     */
    @Override
    public float getSpeed(){
        return SPEED;
    }
    
    
    /**
     * Klonuje obiekt gracza
     * @return Odnosnik do nowego obiektu gracza
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
            System.out.println("Blad! Nie mozna sklonowac gracza!");
        }
        return object;
    }
    
    /**
     * Tworzy nowy obiekt gracza
     * @return odnosnik do nowego obiektu gracza, typu GameObject
     */
    public static GameObject create(){
        return PlayerFactory.create();
    }

    
    //<editor-fold defaultstate="collapsed" desc="Fabryka graczy">
    /**
     * Klasa odpowiedzialna za zaladowanie obrazkow i utworzenie z nich animacji gracza
     * Posiada metode tworzaca nowy obiekt gracza
     */
    private static class PlayerFactory{
        
        static final List<Image> playerMovingUp;
        
        static final List<Image> playerMovingLeft;
        
        static final List<Transformation> transformations;
        
        static final List<Animation> playerAnimation;
        
        static{
            playerMovingUp = ImageLoader.loadImageList("player", "png");
            playerMovingLeft = ImageLoader.loadImageList("player_left", "png");
            transformations = Arrays.asList(new Identity(), new Reverse());
            playerAnimation = new LinkedList<>();
            playerAnimation.addAll(ImageLoader.createAnimationSet(playerMovingLeft, 200, transformations));
            playerAnimation.addAll(ImageLoader.createAnimationSet(playerMovingUp, 200, transformations));
        }
        
        public static GameObject create(){
            return new Player(playerAnimation);
        }
    }//</editor-fold>
    
    
    /**
     * Aktualizacja polozenia, animacji i kierunku gracza w zaleznosci od czasu
     * @param elapsedTime czas od ostatniej aktualizacji
     */
    @Override
    public void update(long elapsedTime){
        super.update(elapsedTime);
    }
    
    
    /**
     * Reakcja na kolizje w poziomie
     */
    @Override
    public void collideHorizontal(){
        setVelocityX(0);
    }
    
    /**
     * Reakcja na kolizje w pionie
     */
    @Override
    public void collideVertical(){
        setVelocityY(0);
    }
    

}
