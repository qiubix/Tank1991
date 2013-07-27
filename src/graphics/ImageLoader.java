/**
 * ImageLoader.java
 * @author Kari
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

import graphics.Reshape.Transformation;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;



/**
 * Ladowanie obiektow graficznych z dysku oraz skladanie ich w animacje
 * @author Kari
 */
public class ImageLoader {

    private static final String IMAGE_DIR = "images/";  
    
    /**
     * Zaladowanie obrazka
     * @param filename nazwa pliku
     * @return Referencja do obrazka
     */
    public static Image loadImage(String filename){
        return new ImageIcon(IMAGE_DIR + filename).getImage();
    }
    
    /**
     * Zaladowanie obrazka
     * @param filename nazwa pliku
     * @return Referencja do obrazka
     */
    public static Icon loadIcon(String filename){
        String fullname = IMAGE_DIR + filename;
        return new ImageIcon(fullname);
    }
    
    /**
     * Funkcja laduje liste obrazkow z dysku. Nazwa kazdego z plikow musi
     * skladac sie z jednakowego prefiksu, liczby porzadkowej pliku oraz jawnego
     * formatu. Przykladowa lista plikow:
     * <pre>image1.jpg, image2.jpg, image3.jpg</pre>
     *
     * @param pattern prefiks plikow
     * @param format format pliku bez kropki
     * @return lista obrazkow
     * @throws IOException 
     */
    public static List<Image> loadImageList(String pattern, String format) {
        List<Image> result = new LinkedList<Image>();
        int index = 0;
        while (true) {
            String filename = pattern + (++index) + "." + format;
            File file;
			try {
				file = openFile(filename);
				result.add(loadImage(filename));
			} catch (IOException e) {
				if(result.isEmpty()) {
					e.printStackTrace(System.err);
					System.err.println("Cannot find pattern: " + pattern);
				}
				else {
					break;
				}
			}
        }
        return result;
    }

	private static File openFile(String filename) throws IOException {
		File file = new File(IMAGE_DIR + filename);
		if(!file.exists())
			throw new IOException("Cannot open file: " + IMAGE_DIR + filename);
		return file;
	}
    
    /**
     * Tworzy liste animacji na podstawie listy obrazkow i mozliwych transformacji
     * @param imageList lista obrazkow, z ktorych zostanie utworzona animacja
     * @param time czas animacji
     * @param transformationList lista dostepnych trasformacji
     * @return 
     */
    public static List<Animation> createAnimationSet(List<Image> imageList, int time, List<Transformation> transformationList){
        List<Animation> result = new ArrayList<>();
        for(Transformation transformation : transformationList){
            result.add(createAnimation(imageList, time, transformation));
        }
        return result;
    }

    
    public static Animation createAnimation(List<Image> imageList, int time, Transformation transformation) {
        Animation animation = new Animation();
        for(Image frame : imageList){
            animation.addFrame(transformation.transform(frame), time);
        }
        return animation;
    }

}
