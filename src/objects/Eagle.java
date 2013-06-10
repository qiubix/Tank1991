/**
 * Eagle.java
 * @author Kari
 */



package objects;

import graphics.Animation;
import graphics.ImageLoader;
import graphics.Reshape.Identity;
import java.awt.Image;
import java.util.List;


/**
 * Klasa reprezentujaca orzelka, cel gry, ktory gracz musi chronic a ktory
 * przeciwnicy chca zniszczyc. "Zabicie" orzelka oznacza koniec gry. 
 * @author Kari
 */
public class Eagle extends DynamicObject{
    
    public enum State{
        ALIVE,DEAD
    }
    
    private State state;
    
    public Eagle(Animation animation){
        super(animation);
        state = State.ALIVE;
    }
    
    
    public State getState(){
        return state;
    }
    
    
    public void setState(State state){
        this.state = state;
    }

    @Override
    public Object clone(){
        return new Eagle((Animation) graphic);
    }
    
    public static GameObject create(){
        return EagleFactory.create();
    }
    
    
    
    private static class EagleFactory{
        static final List<Image> eagleFrames = ImageLoader.loadImageList("eagle", "png");
        
        public static GameObject create(){
            return new Eagle(ImageLoader.createAnimation(eagleFrames, 400, new Identity()));
        }
    }

}
