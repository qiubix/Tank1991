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
        long elapsedTime = getTime() - currentTime;
        currentTime += elapsedTime;
        return elapsedTime;
    }
}
