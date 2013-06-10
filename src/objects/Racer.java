/**
 * Racer.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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



/**
 * Klasa reprezentujaca wrogie czolgi typu Scigacz
 * Roznia sie od zwyklego przeciwnika predkoscia poruszania
 */
public class Racer extends Enemy{

        
    public Racer(List<Animation> animationList){
        super(animationList);
    }
    
    public static GameObject create(){
        return RacerFactory.create();
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Fabryka wrogow - Scigaczy">
    /**
     * Klasa odpowiadajaca za zaladowanie obrazkow i utworzenie z nich animacji wroga
     * Posiada metode pozwalajaca utworzyc nowy obiekt wroga
     */
    private static class RacerFactory{
        static final List<Image> enemyMovingUp;
        
        static final List<Image> enemyMovingLeft;
        
        static final List<Reshape.Transformation> transformations;
        
        static final List<Animation> enemyAnimation;
        
        static{
            enemyMovingUp = ImageLoader.loadImageList("destroyer", "png");
            enemyMovingLeft = ImageLoader.loadImageList("destroyer_left", "png");
            transformations = Arrays.asList(new Reshape.Identity(), new Reshape.Reverse());
            enemyAnimation = new LinkedList<>();
            enemyAnimation.addAll(ImageLoader.createAnimationSet(enemyMovingLeft, 200, transformations));
            enemyAnimation.addAll(ImageLoader.createAnimationSet(enemyMovingUp, 200, transformations));
        }
        
        public static GameObject create(){
            return new Racer(enemyAnimation); 
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

}
