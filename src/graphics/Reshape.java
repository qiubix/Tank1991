/**
 * Reshape.java
 * @author Kari
 */

package graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;



/**
 * Abstrakcyjna klasa zawierajaca dziedziczace po niej statyczne klasy wewnetrzne
 * bedaca klasa bazowa dla geometrycznych przeksztalcen obrazkow. 
 * @author Kari
 */
public abstract class Reshape {
    
    public abstract Image transform(Image image);
    

    protected Image scale(Image image, float x, float y){
        
        //Inicjalizacja transformacji
        AffineTransform transformation = new AffineTransform();
        transformation.scale(x, y);
        transformation.translate(
                (x-1) * image.getWidth(null) / 2,
                (y-1) * image.getHeight(null) / 2);
        //tworzenie przezroczystego obrazka
        Image newImage = new ScreenManager().createCompatibleImage(image.getWidth(null), 
                                                                   image.getHeight(null), 
                                                                   Transparency.BITMASK);
        
        //rysowanie rysunku po transformacji
        Graphics2D g = (Graphics2D) newImage.getGraphics();
        g.drawImage(image, transformation, null);
        g.dispose();
        return newImage;
    }
    
    
    public abstract static class Transformation extends Reshape{

        protected Reshape base;
        
        public Transformation(Reshape base){
            this.base = base;
        }

        public abstract Image transform(Image image);
    }
    
    
    /**
     * Przeksztalcenie tozsamosciowe
     */
    public static class Identity extends Transformation{

        public Identity(){
            super(null);
        }
        
        public Identity(Reshape base){
            super(base);
        }
        
        @Override
        public Image transform(Image image) {
            Image result = image;
            if(base != null){
                result = base.transform(result);
            }
            return result;
        }
    }
    
    
    
    /**
     * Odbicie lustrzane (horyzontalne)
     */
    public static class Mirror extends Transformation{

        public Mirror(){
            super(null);
        }
        
        public Mirror(Reshape base){
            super(base);
        }
        
        @Override
        public Image transform(Image image) {
            Image result = scale(image,-1,1);
            if(base != null){
                result = base.transform(result);
            }
            return result;
        }
    }
    
    
    /**
     * Odbicie lustrzane (wertykalne)
     */
    public static class Flip extends Transformation{

        public Flip(){
            super(null);
        }
        
        public Flip(Reshape base){
            super(base);
        }
        
        @Override
        public Image transform(Image image) {
            Image result = scale(image, 1, -1);
            if(base != null){
                result = base.transform(result);
            }
            return result;
        }
    }
    
    
    
    public static class Reverse extends Transformation{

        public Reverse(){
            super(null);
        }
        
        public Reverse(Reshape base){
            super(base);
        }
        
        @Override
        public Image transform(Image image) {
            Reshape result = new Flip(new Mirror());
            return result.transform(image);
        }
    }
    
    
    
}
