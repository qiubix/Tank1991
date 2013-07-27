package objects;

import graphics.Graphic;


public abstract class DynamicObject extends GameObject{

    /** Predkosc pozioma */
    private float dX;
    /** Predkosc pionowa */
    private float dY;

    public DynamicObject(Graphic graphic){
        super(graphic);
    }

    /**
     * @return the dX
     */
    public float getVelocityX() {
        return dX;
    }

    /**
     * @param dX the dX to set
     */
    public void setVelocityX(float dX) {
        this.dX = dX;
    }

    /**
     * @return the dY
     */
    public float getVelocityY() {
        return dY;
    }

    /**
     * @param dY the dY to set
     */
    public void setVelocityY(float dY) {
        this.dY = dY;
    }

    public void reactToHorizontalCollision(){
    }

    public void reactToVerticalCollision(){
    }
    
    
}
