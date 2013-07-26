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
    private boolean isRunning;
    
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
        
    public void init() {        
        model = new Model();        
        view = new View(model);       
        controller = new Controller(this, model, view);
        
        model.addObserver(view);
        model.addObserver(controller);
        
        isRunning = true;
    }
    public void gameLoop() {
        Timer timer = new Timer();
        while(isRunning){
            model.update(timer.tick());
            sleep(20);
        }
    }

	private void sleep(int duration) {
		try{
			Thread.sleep(duration);
		}
		catch(InterruptedException ex){
		    ex.printStackTrace(System.out);
		}
	}
	
    public void stop(){
        isRunning = false;
    }
    
}
