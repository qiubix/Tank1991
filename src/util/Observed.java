/**
 * Observed.java
 * @author Kari
 */


package util;

import java.util.LinkedList;
import java.util.List;

/**
 * Wzorzec projektowy Obserwator
 * Klasa obiektow bedacych przedmiotem obserwacji ( obserwowanych )
 * @author Kari
 */
public class Observed {
    
    /**
     * Kontener przechowujacy obiekty obserwujace dany obiekt
     */
    private List<Observer> observers = new LinkedList<>();
    
    
    /**
     * Dodanie nowego obserwatora do listy
     * @param observer nowy obserwator danego obiektu
     */
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    
    
    /**
     * Usuwanie obserwatora z listy
     * @param observer obserwator, ktorego chcemy usunac
     * @return informacja o tym, czy usuniecie nastapilo. Zwraca false, jesli 
     * nie ma takiego obserwatora na liscie. 
     */
    public boolean removeObserver(Observer observer){
        return observers.remove(observer);
    }
    
    
    /**
     * Wyczyszczenie listy obserwatorow
     */
    public void clearObserversList(){
        observers.clear();
    }
    
    
    /**
     * Poinformowanie obserwatorow o zmianie stanu biezacego obiektu
     */
    public void signalToObservers(){
        for(Observer o : observers){
            o.signal(this);
        }
    }
    
}
