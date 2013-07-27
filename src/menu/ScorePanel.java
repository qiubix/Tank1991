/**
 * ScorePanel.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;


//TODO: Change constructor (Factory?)
public class ScorePanel extends JPanel{
    
    private Point position;
    
    public GraphicCounter lifesCounter;
    
    public GraphicCounter enemiesCounter;
    
    public GraphicCounter levelCounter;
    
    public GraphicCounter pointCounter;
    
    public ScorePanel(  GraphicCounter levelCounter, 
                        GraphicCounter lifesCounter, 
                        GraphicCounter enemiesCounter, 
                        GraphicCounter pointCounter,
                        Point position){
        this.levelCounter = levelCounter;
        this.lifesCounter = lifesCounter;
        this.enemiesCounter = enemiesCounter;
        this.pointCounter = pointCounter;
        this.position = position;
        setOpaque(true);
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(levelCounter.getIcon(), position.x, 10, null);
        g.drawString("Poziom " + levelCounter.get(), position.x + levelCounter.getIcon().getWidth(null), position.y);
        g.drawImage(lifesCounter.getIcon(), position.x * 6, 10, null);
        g.drawString(" x " + lifesCounter.get(), position.x * 6 + lifesCounter.getIcon().getWidth(null), position.y);
        g.drawImage(enemiesCounter.getIcon(), position.x * 10, 10, null);
        g.drawString(" x " +enemiesCounter.get(), position.x * 10 + enemiesCounter.getIcon().getWidth(null), position.y);
        g.drawImage(pointCounter.getIcon(), position.x * 15, 10, null);
        g.drawString(" x " +pointCounter.get(), position.x * 15 + pointCounter.getIcon().getWidth(null), position.y);
        
    }

}
