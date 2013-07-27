/**
 * LevelLoader.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package level;

import graphics.ImageLoader;

import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import objects.Eagle;
import objects.Enemy;
import objects.GameObject;
import objects.Player;
import util.Factory;


public class LevelLoader {
    
    
    /** Poziom gry     */
    private Level map;
    
    /** Lista dostepnych obrazkow     */
    private List<Image> blocks;
    
    
    /** Numer aktualnego poziomu gry     */
    private int currentLevel;
    
    
    /** Sciezka dostepu do map gry     */
    private static final String LEVEL_DIR = "maps/";
    
    
    /** Lista dostepnych w grze klas pochodnych od GameObject     */
    private static final List<Class> listOfClasses = new LinkedList<Class>();
    

    /**
     * Mapa zawiera powiazania miedzy obiektami Class a ich pierwowzorami, 
     * czyli ich instancjami bedacymi juz w grze. 
     */
    private static final Map<Class, GameObject> objectPrototypes = 
            new HashMap<Class, GameObject>();
    
    
    
    /**
     * Konstruktor menagera poziomow gry. 
     * Inicjalizuje liste dostepnych obrazkow, liste obiektow dostepnych w grze 
     * oraz mape powiazan tych obiektow z ich pierwowzorami
     */
    public LevelLoader(){
        loadBlocks();
        loadClasses();
        loadGameObjects();
    }
    
    /**
     * Laduje obrazki klockow, z ktorych jest zbudowana plansza,
     * do listy dostepnych obrazkow, z ktorych mozna budowac
     */
    private void loadBlocks(){
        try {
			blocks = ImageLoader.loadImageList("brick", "png");
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
    }
    
    /**
     * Laduje na liste klasy obiektow gry
     */
    private void loadClasses(){
        listOfClasses.addAll(Arrays.asList( Player.class, 
                                            Enemy.class, 
                                            Eagle.class));
    }
    
    
    /**
     * Wiaze pojedyncza instancje obiektu z klasa danego obiektu
     */
    private void loadGameObjects(){
        Factory factory = Factory.getInstance();
        for(Class c : listOfClasses){
            factory.register(c);
            objectPrototypes.put(c, (GameObject) factory.create(c));
        }
    }
    
    
    /**
     * Reset gry - rozpoczecie od nowa, a wiec aktualny poziom jest ustawiany na 0
     */
    public void reset(){
        currentLevel = 0;
    }
    
    /**
     * Funkcja okresla, czy istnieje nastepna mapa do zaladowania.
     */
    public boolean isNextLevel() {
        return (new File(LEVEL_DIR + "map" + (currentLevel + 1))).exists();
    }
    
    /**
     * Wczytanie nastepnego poziomu gry
     * @return nowa mapa
     */
    public Level loadNextLevel(){
        try{
            return loadMap(LEVEL_DIR + "map"+ (++currentLevel));
        }
        catch(IOException ex){
            ex.printStackTrace(System.err);
            throw new RuntimeException("Cannot find map" + currentLevel);
        }
    }

    /**
     * Funkcja wczytuje ponownie biezaca mape poziomu.
     * @return biezaca mapa
     */
    public Level reloadLevel() {
        try {
            return loadMap(LEVEL_DIR + "map" + currentLevel);
        } catch (IOException ex) {
            throw new RuntimeException("Cannot find map" + currentLevel);
        }
    }
    
    /**
     * Pobranie numeru poziomu
     * @return numer aktualnego poziomu gry
     */
    public int getLevelNumber(){
        return currentLevel;
    }

    
    /**
     * Ladowanie mapy.
     * @param filename nazwa pliku
     * @return nowy obiekt typu Level z wczytanym aktualnym poziomem
     * @throws IOException w przypadku nie znalezienia pliku mapy o danej nazwie
     */
    private Level loadMap(String filename) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        int width = 0;
        int height = 0;

        //Odczytanie wszystkich wierszy z pliku
             	
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                reader.close();
                break;
            }
            if (!line.startsWith("#")) {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }

        height = lines.size();
        map = new Level();
        Class object = null;
        boolean isVolatile = false;
        int enemiesCount = 0;

        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char ch = line.charAt(x);
                if (Character.isLowerCase(ch)) {
                    isVolatile = true;
                    ch = Character.toUpperCase(ch);
                } else {
                    isVolatile = false;
                }
                int block = ch - 'A';
                if (block >= 0 && block < blocks.size()) {
                    map.setField(new Point(x, y), blocks.get(block), isVolatile);
                }
                switch (ch) {
                    case '$':
                        object = Eagle.class;
                        break;
                    case '1':
                        object = Enemy.class;
                        enemiesCount++;
                        break;
                    default:
                        continue;
                }
                //dodanie obiektu do mapy
                addGameObject(map, object, x, y);

            }
        }

        //Pobranie obrazka tla i ustawienie tla mapy
        Image bg = ImageLoader.loadImage("bg_big3.jpg");
        map.setBackground(bg);

        //Wstawienie graca
        Player newPlayer;
//        newPlayer = (Player)Player.create();
        newPlayer = (Player) getGameObject(Player.class).clone();
        newPlayer.setX(Level.fieldsToPixels(7));
        newPlayer.setY(Level.fieldsToPixels(11));
        map.setPlayer(newPlayer);

        //Model.setEnemiesCount(enemiesCount);

        return map;
    }
    
    /**
     * Dodaje obiekt do mapie na podstawie informacji o klasie
     * @param map mapa, do ktorej chcemy dodac obiekt
     * @param classInfo nazwa pliku z klasa obiektu, ktory chcemy dodac
     * @param positionX pozycja pozioma, na ktorej ma byc ustawiony obiekt
     * @param positionY pozycja pionowa, na ktorej ma byc ustawiony obiekt. 
     */
    private void addGameObject(Level map, Class classInfo, int positionX, int positionY){
        GameObject object = (GameObject) getGameObject(classInfo).clone();
        if (object != null) {
			// ustawienie obiektu w okreslonym punkcie mapy
			object.setX(Level.fieldsToPixels(positionX) - object.getWidth()
					+ Level.FIELD_SIZE);
			object.setY(Level.fieldsToPixels(positionY) - object.getHeight()
					+ Level.FIELD_SIZE);
            // dodanie obiektu do mapy
            map.addGameObject(object);
        }
    }

    
    /**
     * Pobiera obiekt gry na podstawie informacji o klasie
     * @param classInfo Nazwa pliku z klasa
     * @return Zwraca GameObject z mapy prototypow na podstawie informacji o klasie. 
     */
    private GameObject getGameObject(Class classInfo) {
        return objectPrototypes.get(classInfo);
    }
    
    /**
     * Funkcja dodaje nowego wroga do mapy
     * @param map poziom, do ktorego chcemy dodac wroga
     * @param classInfo nazwa klasy wroga, ktorego chcemy dodac
     */
    public void addNewEnemy(Level map, Class classInfo){
        Random rand = new Random(13);
        switch(rand.nextInt(3)){
            case 0: addGameObject(map, classInfo, 2, 0); break;
            case 1: addGameObject(map, classInfo, 11, 0); break;
            case 2: addGameObject(map, classInfo, 20, 0); break;
        }
    }
    
}
