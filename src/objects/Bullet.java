package objects;

import graphics.Graphic;

public class Bullet extends DynamicObject{

    private static final float SPEED = .6f;
    
    private long lastShot;
    
    private Tank tank;
    
    public Bullet(Graphic graphic, Tank tank){
        super(graphic);
        this.tank = tank;
        lastShot = System.currentTimeMillis();
    }
    
    public float getSpeed(){
        return SPEED;
    }
    
    public void setLastShotTime(long time){
        lastShot = time;
    }
    
    public long getLastShotTime(){
        return lastShot;
    }
    
    public Tank getTank(){
        return tank;
    }
    
   
    @Override
    public void collideHorizontal(){
        setVelocityX(0);
        tank.tankShoots(false);
    }
    
    
    @Override
    public void collideVertical(){
        setVelocityY(0);
        tank.tankShoots(false);
    }
    
}
