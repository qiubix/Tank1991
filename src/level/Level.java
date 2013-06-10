/**
 * Level.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package level;

import graphics.Drawable;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import objects.Enemy;
import objects.GameObject;
import objects.Player;
import util.PriorityList;


public class Level extends JPanel implements Drawable{
    
    /** Przechowuje obrazki pol     */
    private Image[][] fields;
    
    /** Lista pol, ktore mozna zniszczyc */
    private List<Point> destroyableFields;
    
    /** Lista obiektow gry znajdujacych sie na mapie */
    private PriorityList<GameObject> listOfGameObjects;
    
    /** Odnosnik do obiektu gracza */
    private Player player;
    
    /** Obrazek tla poziomu */
    private Image background;
    
    /** Rozmiar pojedynczego pola */
    public static final int FIELD_SIZE = 64;
    
    
    private static final int FIELD_BITS = 6;
    
    /**
     *  Konwersja wspolrzednych pikselowych na wspolrzedne pola.
     */
    public static int pixelsToFields(int pixels) {
        return pixels >> FIELD_BITS;
    }

    /**
     *  Konwersja wspolrzednych pol na wspolprzedne pikselowe.
     */
    public static int fieldsToPixels(int fields) {
        return fields << FIELD_BITS;
    }
    
    
    /**
     * Konstruktor poziomu. Inicjalizuje tablice pol, liste pol mozliwych do zniszczenia
     * oraz liste obiektow gry obecnych na mapie
     */
    public Level(){
        fields = new Image[23][16];
        destroyableFields = new ArrayList<>();
        listOfGameObjects = new PriorityList(1);
    }
    
    
    @Override
    public int getWidth(){
        return fields.length;
    }
    
    @Override
    public int getHeight(){
        return fields[0].length;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Edycja pol mapy">
    /**
     * Pobranie pola o okreslonych wspolrzednych
     * @param x
     * @param y
     * @return 
     */
    public Image getField(int x, int y){
        if( x < 0 || x >= getWidth() || y < 0 || y >= getHeight()){
            return null;
        }
        else{
            return fields[x][y];
        }
    }
    
    /**
     * Ustawia dane pole. 
     * @param point punkt
     * @param image obrazek pola
     * @param isDestroyable jesli wstawiany obiekt jest zniszczalny, to dodajemy 
     * jego wspolrzedna do listy zniszczalnych pol. Jesli nie jest, to z tej listy 
     * usuwamy ten punkt. Nastapila sytuacja, kiedy zastepujemy pole zniszczalne polem
     * niezniszczalnym. 
     */
    public void setField(Point point, Image image, boolean isDestroyable){
        fields[point.x][point.y] = image;
        if(isDestroyable){
            destroyableFields.add(point);
        }
        else{
            destroyableFields.remove(point);
        }
    }
    
    /**
     * Niszczy pole, usuwa je z tablicy pol, ktore mozna zniszczyc i ustawia obrazek
     * na danym polu na null
     * @param position pozycja pola, ktore chcemy zniszczyc
     */
    public void destroyField(Point position){
       if(destroyableFields.contains(position)){
           fields[position.x][position.y] = null;
           destroyableFields.remove(position);
       }
       
    }//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Czolg gracza">
    /**
     * Zwraca gracza 
     * @return odnosnik do obiektu gracza znajdujacego sie na danym poziomie
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Pobranie odnosnika do obiektu gracza, ktorego chcemy ustawic na danym poziomie
     * @param player 
     */
    public void setPlayer(Player player){
        this.player = player;
    }//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Tlo poziomu">
    /**
     * Ustawia tlo poziomu
     * @param background obrazek nowego tla, ktore chcemy ustawic
     */
    public void setBackground(Image background){
        this.background = background;
    }
    
    /**
     * Zwraca tlo poziomu
     * @return 
     */
    public Image getLandscape(){
        return background;
    }//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Zmiany na liscie obiektow gry">
    /**
     * Dodanie nowego obiektu gry do listy dostepnych obiektow gry
     * @param object nowy obiekt, ktory chcemy dodac do listy
     */
    public void addGameObject(GameObject object){
        listOfGameObjects.addFirst(object);
    }
    
    /**
     * Usuniecie z listy obiektow gry danego obiektu
     * @param object obiekt, ktory chcemy usunac
     */
    public void removeGameObject(GameObject object){
        listOfGameObjects.remove(object);
    }//</editor-fold>
    
    /**
     * Funkcja zwraca obiekt Iterator dla kontera przechowujacego obiekty gry,
	 * znajdujace sie na mapie.
    */
    public Iterator iterator() {
        return listOfGameObjects.iterator();
    }

    
    /**
     * Narysowanie aktualnego poziomu na ekranie
     * @param g aktualny kontekst graficzny
     * @param width szerokosc ekranu
     * @param height wysokosc ekranu
     */
    @Override
    public void draw(Graphics2D g, int width, int height) {
        g.drawImage(background, 0, 0, null);
        for(int y=0; y < getHeight(); y++){
            for(int x=0; x< getWidth(); x++){
                Image image = getField(x, y);
                if(image != null){
                    g.drawImage(image, fieldsToPixels(x), fieldsToPixels(y), null);
                }
            }
        }
        g.drawImage(player.getImage(), player.getX(), player.getY(), null);
        if(player.isShooting()){
            g.drawImage(player.getBullet().getImage(), player.getBullet().getX(), player.getBullet().getY(), null);
        }
        // narysowanie obiektow gry
        for (Iterator i = iterator(); i.hasNext();) {
            GameObject object = (GameObject) i.next();
            g.drawImage(object.getImage(), object.getX(), object.getY(), null);
            // aktywowanie obiektu, gdy znajdzie sie na ekranie
            object.activate();
            
            if (object instanceof Enemy) {
                if (((Enemy) object).isShooting()) {
                    g.drawImage(((Enemy) object).getBullet().getImage(),
                            ((Enemy) object).getBullet().getX(),
                            ((Enemy) object).getBullet().getY(),
                            null);
                }
            }

        }
    }

}
