/**
 * Factory.java
 * @author Kari
 */

package util;



import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa reprezentuje szablon singletona fabryki obiektow z rejestrowaniem.
 * Obiekty, ktore maja zostac dodane do fabryki musza implementowac funkcje
 * o nazwie create.
 */
public class Factory {

	/**
	 * Kontener asocjacyjny zawierajacy przyporzadkowania typu (klucz, metoda
	 * fabryczna).
	 */
	protected static Map<Integer, Method> bindings =
			new HashMap<Integer, Method>();

	/**
	 * Jedyna instancja fabryki.
	 */
	private static Factory instance = new Factory();

	/**
	 * Konstruktor prywatny (singleton).
	 */
	private Factory() {}

	/**
	 * Funkcja zwraca jedyny egzemplarz fabryki.
	 */
	public static Factory getInstance() {
		return instance;
	}

	/**
	 * Funkcja produkuje konkretne obiekty na podstawie ich obiektow Class.
	 */
	public Object create(Class classInfo) {
		Object result = null;
		Method method = bindings.get(getId(classInfo));
		if (method != null) {
			try {
				result = method.invoke(null);
			} catch (Exception ex) {
				System.out.println("Error: Factory.create()");
			}
		}
		return result;
	}

	/**
	 * Funkcja rejestruje obiekty w fabryce na podstawie obiektu Class.
	 * @return true, jesli operacja powiodla sie, false, jesli obiekt nie
	 * implementuje funkcji create
	 */
	public boolean register(Class classInfo) {
		boolean result = false;
		int id = getId(classInfo);
		if (bindings.get(id) == null) {
			try {
				Method method = classInfo.getMethod("create");
				bindings.put(id, method);
				result = true;
			} catch (Exception ex) {
				System.out.println("Error: Factory.register()");
			}
		}
		return result;
	}

	/**
	 * Funkcja wyrejestrowuje obiekty z fabryki na podstawie obiektu Class.
	 * @return true, jesli operacja powiodla sie, false, jesli nie zarejestrowano
	 * takiego obiektu.
	 */
	public boolean unregister(Class classInfo) {
		return bindings.remove(getId(classInfo)) != null;

	}

	/**
	 * Funkcja generuje unikalny identyfikator dla obiektu Class. Jest to klucz
	 * w kontenerze asocjacyjnym zawierajacym metody fabryczne.
	 * @return unikalny identyfikator
	 */
	private int getId(Class classInfo) {
		return classInfo.hashCode();
	}

}
