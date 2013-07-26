/**
 * GameCore.java
 * @author Kari
 */


package tank1991;

import util.Timer;


/**
 * Rdzen gry. Realizuje glowna petle, odpowiada za zarzadzanie przeplywem sterowania,
 * inicjalizuje model, widok i kontroler
 * @author Kari
 */
public class GameCore {
    
    private Model model;
    
    private View view;
    
    private Controller controller;
     
    /**Okresla aktywnosc aplikacji     */
    private boolean isRunning;
    
    /**
     * Sygnalizacja petli gry, ze nalezy zakonczyc prace
     */
    public void stop(){
        isRunning = false;
    }
    
    
    /**
     * Metoda run() uruchamiana z chwila wlaczenia gry, 
     * wywoluje init() i gameLoop()
     */
    public void run(){
        try{
            init();
            gameLoop();
        }
        catch (NullPointerException ex) {
        	ex.printStackTrace(System.err);
        }
        finally{
        	view.getScreen().restoreScreen();       		
        }
    }

    
    /**
     * Ustawienie trybu pelnoekranowego i inicjalizacja obiektu okna
     */
    public void init() {
        
        model = new Model();
        
        view = new View(model);
        
        controller = new Controller(this, model, view);
        
        model.addObserver(view);
        model.addObserver(controller);
        
        //mozna wchodzic do petli gry
        isRunning = true;
    }
    
    
    /**
     * Uruchomienie petli gry, dzialajacej az do wywolania metody stop()
     */
    public void gameLoop() {
        Timer timer = new Timer();
        while(isRunning){
                       
            //Aktualizacja
            model.update(timer.tick());
            
            //Chwila przerwy:
            try{
                Thread.sleep(20);
            }
            catch(InterruptedException ex){
                ex.printStackTrace(System.out);
            }
        }
    }

    
}
