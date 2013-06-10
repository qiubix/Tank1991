/**
 * Keyboard.java
 * @author Kari
 */


package event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;


public class Keyboard implements KeyListener{
    
    
    /**
     * Mapa kojarzaca kody klawiszy ze zdarzeniami typu GameAction
     */
    private Map<Integer, GameAction> keyActions = new HashMap<Integer, GameAction>();
    
    /**
     * Skojarzenie obiektu GameAction z okreslonym klawiszem. 
     * Jesli z danym klawiszem jest juz skojarzona jakas akcja, to
     * nowa akcja ja zastepuje. 
     * @param gameAction akcja 
     * @param keyCode kod klawisza
     */
    public void mapToKey(GameAction gameAction, int keyCode){
        keyActions.put(keyCode, gameAction);
    }
    
    
    public void clearMap(GameAction gameAction){
        for(GameAction action : keyActions.values()){
            if(action == gameAction){
                keyActions.remove(action);
            }
        }
        
        gameAction.reset();
    }
    
    
    
    
    /**
     * Pobieranie nazwy kodu zdarzenia klawisza
     * @param keyCode
     * @return 
     */
    public String getKeyName(int keyCode) {
        return KeyEvent.getKeyText(keyCode);
    }
    
    
    /**
     * Zwalnia wszystkie nacisniete klawisze
     */
    public void reset(){
        for(GameAction action : keyActions.values()){
            action.release();
        }
    }
    
    
    
    /**
     * Zwraca obiekt GameAction przypisany do danego klawisza
     * @param e zdarzenie klawisza
     * @return jesli znajdzie przypisana akcje, to ja zwraca, jesli nie, to null
     */
    private GameAction getKeyAction(KeyEvent e){
        return keyActions.get(e.getKeyCode());
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Obsluga zdarzen klawiszy">
    @Override
    public void keyTyped(KeyEvent e) {
        //Upewnienie sie, ze zdarzenie nie bedzie dalej obslugiwane
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        GameAction gameAction = getKeyAction(e);
        if(gameAction != null){
            gameAction.press();
        }
        //Upewnienie sie, ze zdarzenie nie bedzie dalej obslugiwane
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        GameAction gameAction = getKeyAction(e);
        if(gameAction != null){
            gameAction.release();
        }
        //Upewnienie sie, ze zdarzenie nie bedzie dalej obslugiwane
        e.consume();
    }//</editor-fold>

}
