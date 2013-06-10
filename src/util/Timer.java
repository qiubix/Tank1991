/**
 * Timer.java
 * @author Kari
 */


package util;


/**
 * Klasa odmierzajaca czas
 * @author Kari
 */
public class Timer {

    /**
     * Aktualny czas (od 1.01.1970)
     * [milisekundy]
     */
    private long currentTime;
    
    /**
     * Czas, ktory uplynal miedzy kolejnymi pomiarami
     */
    private long elapsedTime;
    
    
    /**
     * Konstruktor klasy Timer, ktory jednoczesnie rozpoczyna
     * odmierzanie czasu
     */
    public Timer(){
        currentTime = getTime();
    }
    
    
    /**
     * Funkcja pobierajaca aktualny czas
     * @return 
     */
    public final long getTime(){
        return System.currentTimeMillis();
    }
    
    
    
    /**
     * Tykniecie zegara
     * @return czas w milisekundach od poprzedniego odmierzenia
     */
    public long tick(){
        elapsedTime = getTime() - currentTime;
        currentTime += elapsedTime;
        return elapsedTime;
    }
}
