/**
 * Animation.java
 * @author Kari
 */

package graphics;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Animation zarzadza seria rysunkow (ramek) oraz
 * czasem wyswietlania kazdej z ramek
 */
public class Animation extends Graphic{
    private List<AnimFrame> frames;
    private int currentFrameIndex;
    private long animationTime;
    
    /**
     * Stworzenie nowego pustego obiektu Animation
     */
    public Animation(){
        frames = new ArrayList<AnimFrame>();
        animationTime = 0;
        start();
    }
    
    public Animation(List<AnimFrame> frames, long animationTime){
        this.frames = frames;
        this.animationTime = animationTime;
        start();
    }

    @Override
    public int getWidth() {
        return getImage().getWidth(null);
    }

    @Override
    public int getHeight() {
        return getImage().getHeight(null);
    }

    @Override
    public Object clone() {
        return new Animation(frames, animationTime);
    }
    
    /**
     * klasa wewnetrzna przechowujaca obrazek i czas jego trwania
     */
    private class AnimFrame{
        Image image;
        long endTime;
        AnimFrame(Image image, long endTime){
            this.image = image;
            this.endTime = endTime;
        }
    }
    
    /**
     * Dodaje do animacji rysunek o okreslonym czasie wyswietlania
     */
    public synchronized void addFrame(Image image, long duration){
        animationTime += duration;
        frames.add(new AnimFrame(image,animationTime));
    }
    
    /**
     * uruchomienie animacji od poczatku. wyzerowanie zmiennych
     * animationTime i currentFrameIndex
     */
    public synchronized void start(){
        animationTime = 0;
        currentFrameIndex = 0;
    }
    
    /**
     * Modyfikuje bierzaca ramke animacji
     */
    @Override
    public synchronized void update(long elapsedTime){
        if(frames.size() > 1){
            animationTime += elapsedTime;
            
            if(animationTime >= animationTime){
                animationTime = animationTime % animationTime;
                currentFrameIndex = 0;
            }
            while(animationTime > getFrame(currentFrameIndex).endTime){
                currentFrameIndex++;
            }
        }
    }
    
    /**
     * pobiera ramke z tablicy
     */
    private AnimFrame getFrame(int i){
        return (AnimFrame)frames.get(i);
    }
    
    /**
     * Pobranie bierzacego rysunku z animacji. 
     * @return Image 
     * @return null gdy animacja nie zawiera rysunkow
     */
    @Override
    public synchronized Image getImage(){
        if(frames.isEmpty()) return null;
        else return getFrame(currentFrameIndex).image;
    }
}
