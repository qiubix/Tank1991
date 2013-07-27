package objects;

import graphics.Graphic;
import java.awt.Image;


public class GameObject {

    protected Graphic graphic;
    
    private int positionX;
    
    private int positionY;
    
    public GameObject(Graphic graphic){
        this.graphic = graphic;
        
    }
    
    
    public Image getImage(){
        return graphic.getImage();
    }
    
    
    public int getWidth(){
        return graphic.getWidth();
    }
    
    
    public int getHeight(){
        return graphic.getHeight();
    }
    
    
    @Override
    public Object clone(){
        return new GameObject(graphic);
    }

    /**
     * @return the positionX
     */
    public int getX() {
        return positionX;
    }

    /**
     * @param positionX the positionX to set
     */
    public void setX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * @return the positionY
     */
    public int getY() {
        return positionY;
    }

    /**
     * @param positionY the positionY to set
     */
    public void setY(int positionY) {
        this.positionY = positionY;
    }
    
    
    public void update(long elapsedTime){
        graphic.update(elapsedTime);
    }
    
    
    public void activate(){
        
    }
    
}
