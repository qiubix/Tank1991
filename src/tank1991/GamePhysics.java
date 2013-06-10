/**
 * GamePhysics.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tank1991;

import java.awt.Point;
import java.util.Iterator;
import level.Level;
import objects.*;


public class GamePhysics {
    
    
    public void update(Model model, DynamicObject object, long elapsedTime) {

        object.update(elapsedTime);
            //Zmiana wspolrzednej poziomej x.
            float dx = object.getVelocityX();
            int oldX = object.getX();
            int newX = (int) (oldX + dx * elapsedTime);
            
            Point tile = getCollidingField(model.getLevel(), object, newX, object.getY());
            if (object.getX() >= 0 && object.getX() + object.getWidth() <= model.getScreenWidth()) {
                if (tile == null) {
                    object.setX(newX);
                } else {
                    if (dx > 0) {
                        object.setX(Level.fieldsToPixels(tile.x) - object.getWidth());
                    } else if (dx < 0) {
                        object.setX(Level.fieldsToPixels(tile.x + 1));
                    }
                    
                    //Nastapilo zderzenie w poziomie
                    if (object instanceof Enemy) {
//                        System.out.println("wrog");
                        ((Enemy) object).collideVertical();
                    } else {
                        object.collideVertical();
                    }
                    //Sprawdzenie, czy obiekt jest kula, jesli tak, to niszczy obiekt, 
                    //w ktory trafil, jesli jest zniszczalny
                    if (object instanceof Bullet) {
                        model.getLevel().destroyField(tile);
                    }
                }
            } else {
                //Obiekt poza ekranem (w poziomie). Wywolanie akcji dla zderzenia w poziomie
                object.collideHorizontal();
            }

            //Zmiana wspolrzednej pionowej y.
            float dy = object.getVelocityY();
            int oldY = object.getY();
            int newY = (int) (oldY + dy * elapsedTime);
            tile = getCollidingField(model.getLevel(), object, object.getX(), newY);
            
            if (newY >= 0 && newY + object.getHeight() <= model.getScreenHeight()) {
                if (tile == null) {
                    object.setY(newY);
                } else {
                    if (dy > 0) {
                        object.setY(Level.fieldsToPixels(tile.y) - object.getHeight());
                    } else if (dy < 0) {
                        object.setY(Level.fieldsToPixels(tile.y + 1));
                    }
                    
                    //Nastapilo zderzenie w pionie
                    if (object instanceof Enemy) {
//                        System.out.println("wrog");
                        ((Enemy) object).collideVertical();
                    } else {
                        object.collideVertical();
                    }
                    //collisionVertical(object);
                    //Sprawdzenie, czy obiekt jest kula, jesli tak, to niszczy obiekt, 
                    //w ktory trafil, jesli jest zniszczalny
                    if (object instanceof Bullet) {
                        model.getLevel().destroyField(tile);
                    }
                }
            } else {
                object.collideVertical();
            }
            
            DynamicObject collidingObject = getCollidingObject(model.getLevel(), object);
            if(collidingObject != null){
                if(dx != 0){
                    object.collideHorizontal();
                    if(object instanceof Bullet && collidingObject instanceof Enemy){
                        if (!(((Bullet) object).getTank() instanceof Enemy)) {
                            ((Enemy) collidingObject).setState(Tank.State.DEAD);
                        }
                    }
                    if(object instanceof Player && collidingObject instanceof Tank){
//                        System.out.println("Zderzenie w poziomie");
                    }
                    if(object instanceof Bullet && collidingObject instanceof Eagle){
                        model.setGameState(Model.GameState.LOSS);
                    }
                    if(object instanceof Bullet && collidingObject instanceof Bullet){
                        ((Bullet)object).collideHorizontal();
                        ((Bullet)collidingObject).collideHorizontal();
                    }
                }
                if(dy != 0){
                    object.collideVertical();
                    if(object instanceof Bullet && collidingObject instanceof Enemy){
                        if (!(((Bullet) object).getTank() instanceof Enemy)) {
                            ((Enemy) collidingObject).setState(Tank.State.DEAD);
                        }
                    }
                    if(object instanceof Player && collidingObject instanceof Tank){
//                        System.out.println("Zderzenie w pionie");
//                        if (dy > 0) {
//                            object.setY(Level.fieldsToPixels(collidingObject.getY()) + object.getHeight());
//                        } else if (dy < 0) {
//                            object.setY(Level.fieldsToPixels(collidingObject.getY() + 1));
//                        }
                    }
                    if(object instanceof Bullet && collidingObject instanceof Eagle){
                        model.setGameState(Model.GameState.LOSS);
                    }
                    if(object instanceof Bullet && collidingObject instanceof Bullet){
                        ((Bullet)object).collideVertical();
                        ((Bullet)collidingObject).collideVertical();
                    }
                }
            }
            
    }
    
    
    
    /**
     * Obliczenie pola kolidujacego z danym obiektem dynamicznym
     * @param map mapa obiektu. Zawiera informacje o polach. 
     * @param tank dynamiczny obiekt dla ktorego sprawdzamy kolizje
     * @param newX pozycja w poziomie, na ktora chcemy przesunac obiekt
     * @param newY pozycja w pionie, na ktora chcemy przesunac obiekt
     * @return pozycja pola kolidujacego z danym obiektem dynamicznym. Zwraca null gdy nie znajdzie kolizji
     */
    private Point getCollidingField(Level map, DynamicObject tank, int newX, int newY){
        Point collidingField = null;
        
        //Wyznaczenie obszaru potencjalnej kolizji w pikselach
        int fromX = Math.min(tank.getX(), newX);
        int toX = Math.max(tank.getX(), newX);
        int fromY = Math.min(tank.getY(), newY);
        int toY = Math.max(tank.getY(), newY);
        
        
        //Wyznaczenie obszaru potencjalnej kolizji w polach
        int fromTileX = Level.pixelsToFields(fromX);
        int fromTileY = Level.pixelsToFields(fromY);
        int toTileX = Level.pixelsToFields(toX + tank.getWidth() - 1);
        int toTileY = Level.pixelsToFields(toY + tank.getHeight() - 1);
        
        //Sprawdzenie kolizji ze wszystkimi mozliwymi polami
        for(int x = fromTileX; x <= toTileX; x++){
            for(int y = fromTileY; y <= toTileY; y++){
                if( x < 0 || x >= map.getWidth() || map.getField(x, y) != null){
                    //znaleziono kolizje
                    collidingField = new Point(x, y);
//                    System.out.println("Znaleziono kolizje z polem: field[" + x + "][" + y + "]");
                }
            }
        }
        
        return collidingField;
    }
    
    
    /**
     * Sprawdza kolizje miedzy dwoma obiektami dynamicznymi
     * @param o1 piewszy obiekt dynamiczny
     * @param o2 drugi obiekt dynamiczny
     * @return true, jesli wystapila kolizja
     */
    public boolean isCollision(DynamicObject o1, DynamicObject o2){
        //Sprawdzenie, czy obiekty sa takie same
        if(o1 == o2){
            return false;
        }
        
        //Pobranie polozenia obiektow
        int o1x = Math.round(o1.getX());
        int o1y = Math.round(o1.getY());
        int o2x = Math.round(o2.getX());
        int o2y = Math.round(o2.getY());
        
        //Sprawdzenie, czy obiekty nachodza na siebie, czyli czy granice sie przecinaja
        return (o1x < o2x + o2.getWidth() && 
                o2x < o1x + o1.getWidth() && 
                o1y < o2y + o2.getHeight() && 
                o2y < o1y + o1.getHeight());
    }


    /**
     * Wyrownanie czolgu do granic pola
     * @param map aktualny poziom
     * @param tank odnosnik do obiektu czolgu, ktory chcemy wyrownac
     */
    public static void alignObject(Level map, Tank tank){
        Point position = getNearestField(map, tank.getX() + tank.getWidth()/2 -1, tank.getY() + tank.getHeight()/2 - 1);
        tank.setX(position.x);
        tank.setY(position.y);
    }
    
    
    /**
     * Obliczenie wspolrzednych najblizszego pola
     * @param map poziom
     * @param x wspolrzedna pozioma
     * @param y wspolrzedna pionowa
     * @return pozycja najblizszego pola dla podanych wspolrzednych
     */
    private static Point getNearestField(Level map, int x, int y){
        int positionX = Level.pixelsToFields(x);
        int positionY = Level.pixelsToFields(y);
        Point result = new Point(Level.fieldsToPixels(positionX), Level.fieldsToPixels(positionY));
        return result;
    }
    
    
    /**
     * Sprawdzenie kolizji obiektu dynamicznego z innymi obiektami mapy
     * @param map dany poziom
     * @param object obiekt, dla ktorego chcemy sprawdzic kolizje z innymi obiektami
     * @return odnosnik do obiektu, z ktorym koliduje object
     */
    public DynamicObject getCollidingObject(Level map, DynamicObject object){
        DynamicObject collidingObject = null;
        for(Iterator i = map.iterator();i.hasNext();){
            DynamicObject other = (DynamicObject) i.next();
            if(isCollision(object, other)){
                    collidingObject = other;
                break;
            }
        }
        if(isCollision(object, map.getPlayer())){
            collidingObject = map.getPlayer();
        }
        return collidingObject;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Reakcja na kolicje obiektu dynamicznego (aktualnie nieuzywane) ">
    private void collisionVertical(DynamicObject object){
        if(object instanceof Enemy){
            ((Enemy)object).collideVertical();
        }
        else{
            object.collideVertical();
        }
    }
    
    
    private void collisionHorizontal(DynamicObject object){
        if(object instanceof Enemy){
            ((Enemy)object).collideHorizontal();
        }
        else{
            object.collideHorizontal();
        }
    }//</editor-fold>
    
    
}


