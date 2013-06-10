/**
 * Observer.java
 * @author Kari
 */


package util;


/**
 * Wzorzec projektowy Obserwator. 
 * Interfejs implementowany przez klasy bedace obserwatorami.
 * @author Kari
 */
public interface Observer {
    
    
    /**
     * Przedmiot bedacy obserwowany, wywoluje metode signal na obserwatorze, 
     * informujac go o zmianie swojego stanu. 
     * @param observed obiektu obserwowany, ktory wywolal te metode. 
     */
    public void signal(Observed observed);

}
