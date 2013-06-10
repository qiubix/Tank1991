/**
 * GameAction.java
 * @author Kari
 */


package event;

/**
 * Abstrakcyjna klasa bedaca baza dla akcji inicjowanych przez uzytkownika, 
 * na przyklad skakania lub poruszania. 
 * @author Kari
 */
public class GameAction {
    
    /**
     * Normalne dzialanie. Metoda isPressed() zwraca wartosc true, dopoki klawisz
     * jest przycisniety
     */
    public static final int NORMAL = 0;
    
    /**
     * Poczatkowa obsluga przycisniecia. Metoda isPressed() zwraca true jedynie 
     * po pierwszym nacisnieciu klawisza
     */
    public static final int DETECT_INITIAL_PRESS_ONLY = 1;
    
    private static final int STATE_RELEASED = 0;
    private static final int STATE_PRESSED = 1;
    private static final int STATE_WAITING_FOR_RELEASE = 2;
    
    private String name;
    private int behaviour;
    private int amount;
    private int state;
    
    
    public GameAction(){
        this(null,NORMAL);
    }
    
    /**
     * Tworzenie nowego obiektu GameAction
     * Dzialanie NORMAL
     * @param name nazwa akcji
     */
    public GameAction(String name){
        this(name, NORMAL);
    }
    
    
    public GameAction(int behaviour){
        this(null, behaviour);
    }
    
    
    /**
     * Tworzenie nowego obiektu GameAction
     * Dzialanie podane
     * @param name nazwa akcji
     * @param behaviour numer zachowania
     */
    public GameAction(String name, int behaviour){
        this.name = name;
        this.behaviour = behaviour;
        reset();
    }
    
    
    /**
     * Zwraca nazwe danego obiektu GameAction
     */
    public String getName(){
        return name;
    }
    
    /**
     * Kasuje stan obiektu GameAction, dzieki czemu zachowuje sie on tak, 
     * jakby nie zostal nacisniety klawisz
     */
    public void reset(){
        state = STATE_RELEASED;
        amount = 0;
    }
    
    
    /**
     * Programowe przycisniecie w bierzacym obiekcie GameAction. 
     * Ma takie samo dzialanie, co wywolanie press() i potem release()
     */
    public synchronized void tap(){
        press();
        release();
    }
    
    /**
     * Sygnalizacja nacisniecia klawisza
     */
    public synchronized void press(){
        press(1);
    }
    
    
    /**
     * Sygnalizacja nacisniecia klawisza okreslona liczbe razy lub przesuniecia
     * myszy o dany dystans
     */
    public synchronized void press(int amount){
        if(state != STATE_WAITING_FOR_RELEASE){
            this.amount += amount;
            state = STATE_PRESSED;
        }
    }
    
    
    /**
     * Sygnalizacja zwolnienia klawisza
     */
    public synchronized void release(){
        state = STATE_RELEASED;
    }
    
    
    /**
     * Zwraca informacje o tym, czy od ostatniego sprawdzenia zostal nacisniety
     * jakis klawisz
     */
    public synchronized boolean isPressed(){
        return (getAmount() != 0);
    }
    
    
    /**
     * Liczba nacisniec klawisza od ostatniego sprawdzenia, 
     * lub odlegosc przesuniecia myszy od ostatniego sprawdzenia
     */
    public synchronized int getAmount(){
        int count = amount;
        if(count != 0){
            if(state == STATE_RELEASED){
                amount = 0;
            }
            else if(behaviour == DETECT_INITIAL_PRESS_ONLY){
                state = STATE_WAITING_FOR_RELEASE;
                amount = 0;
            }
        }
        return count;
    }
}
