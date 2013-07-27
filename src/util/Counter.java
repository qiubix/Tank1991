package util;

public class Counter {

    protected int counter;

    public Counter() {
        counter = 0;
    }
    
    public Counter(int value){
        counter = value;
    }
    
    public Counter(Counter counter){
        this.counter = counter.counter;
    }

    public int get() {
        return counter;
    }

    public void set(int counter) {
        this.counter = counter;
    }

    public int add(int value) {
        return counter += value;
    }

    public void increment() {
        add(1);
    }

    public void decrement() {
        add(-1);
    }

    /**
     * Funkcja resetuje wartosc licznika (ustawia na 0).
     */
    public void reset() {
        set(0);
    }

    /**
     * Funkcja zwraca wartosc licznika w postaci lacucha znakow.
     */
    @Override
    public String toString() {
        return String.valueOf(counter);
    }
}
