package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Lista priorytetowa. Obiekty przechowywane posiadaja priorytety, wedlug
 * ktorych rozstrzygana jest ich kolejnosc na liscie. Priorytety reprezentowane
 * sa przez dodatnie liczby calkowite. Im mniejsza liczba, tym wiekszy priorytet.
 */
public class PriorityList<T> {

	/**
	 * Tablica podlist.
	 */
	protected LinkedList[] priorityList;

	/**
	* Liczba priorytetow.
	*/
	protected int priorities;

	/**
	* Rozmiar listy.
	*/
	protected int size;

	/**
	 * Konstruktor tworzy liste o zadanej liczbie priorytetow, ktore mozna
	 * przyporzadkowywac obiektom przechowywanym na liscie
	 */
	public PriorityList(int priorities) {
		this.priorities = priorities;
		initialize();
	}

	/**
	 * Funkcja dodaje do listy obiekt o podanym priorytecie.
	 */
	public void add(T object, int priority) {
		priorityList[priority].add(object);
		++size;
	}

	/**
	 * Funkcja dodaje do listy podany obiekt z najwyzszym priorytetem.
	 */
	public void addFirst(T object) {
		add(object, 0);
	}

	/**
	 * Funkcja dodaje do listy podany obiekt z najnizszym priorytetem.
	 */
	public void addLast(T object) {
		add(object, priorities - 1);
	}

	/**
	 * Funkcja sprawdza, czy lista zawiera podany obiekt.
	 * @return powodzenie operacji
	 */
	public boolean contains(T object) {
		for (Iterator it = iterator(); it.hasNext(); ) {
			if (object == it.next()) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Funkcja zwraca z listy element o najwyzszym priorytecie (nie jest on
	 * usuwany) lub null, jesli lista jest pusta.
	*/
	public T getFirst() {
		T object = null;
		for (int i = 0; i < priorities; ++i) {
			LinkedList<T> list = priorityList[i];
			if (!list.isEmpty()) {
				object = list.getFirst();
			}
			if (object != null) {
				break;
			}
		}
		return object;
	}

	/**
	 * Funkcja zwraca z listy element o najnizszym priorytecie (nie jest on
	 * usuwany) lub null, jesli lista jest pusta.
	*/
	public T getLast() {
		T object = null;
		for (int i = priorities - 1; i >= 0; --i) {
			LinkedList<T> list = priorityList[i];
			if (!list.isEmpty()) {
				object = list.getLast();
			}
			if (object != null) {
				break;
			}
		}
		return object;
	}

	/**
	* Funkcja zwraca kontener zawierajacy wszystkie elementy listy priorytetowej,
	* ustawione w kolejnosci malejacych priorytetow.
	*/
	public List<T> getAll() {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < priorities; ++i) {
			result.addAll(priorityList[i]);
		}
		return result;
	}

	/**
	 * Funkcja usuwa z listy podany obiekt (jego pierwsze wystapienie).
	 * @return powodzenie operacji
	 */
	public boolean remove(T object) {
		for (Iterator it = iterator(); it.hasNext(); ) {
			if (object == it.next()) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Funkcja usuwa z listy element o najwyzszym priorytecie.
	 * @return usuniety element lub null, jesli lista jest pusta
	*/
	public T removeFirst() {
		T object = null;
		for (int i = 0; i < priorities; ++i) {
			LinkedList<T> list = priorityList[i];
			if (!list.isEmpty()) {
				object = list.removeFirst();
			}
			if (object != null) {
				break;
			}
		}
		if (object != null) {
			--size;
		}
		return object;
	}

	/**
	 * Funkcja usuwa z listy element o najnizszym priorytecie.
	 * @return usuniety element lub null, jesli lista jest pusta
	*/
	public T removeLast() {
		T object = null;
		for (int i = priorities - 1; i >= 0; --i) {
			LinkedList<T> list = priorityList[i];
			if (!list.isEmpty()) {
				object = list.removeLast();
			}
			if (object != null) {
				break;
			}
		}
		if (object != null) {
			--size;
		}
		return object;
	}

	/**
	 * Funkcja usuwa wszystkie elementy z listy.
	*/
	public void clear() {
		initialize();
	}

	/**
	* Funkcja zwraca ilosc elementow listy.
	*/
	public int size() {
		return size;
	}

	/**
	* Funkcja sprawdza, czy lista jest pusta.
	*/
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	* Funkcja inicjuje podlisty wedlug liczby priorytetow.
	*/
	private void initialize() {
		priorityList = new LinkedList[priorities];
		for (int i = 0; i < priorities; ++i) {
			priorityList[i] = new LinkedList();
		}
		size = 0;
	}

	/**
	 * Funkcja zwraca iterator listy priorytetowej.
	*/
	public Iterator iterator() {
		return new PriorityListIterator();
	}

	/**
	 * Iterator listy priorytetowej.
	 */
	class PriorityListIterator implements ListIterator<T> {

		/**
		 * Aktualny numer podlisty.
		 */
		private int index;

		/**
		 * Iterator pomocniczny.
		 */
		private ListIterator<T> iterator;

		PriorityListIterator() {
			index = 0;
			iterator = priorityList[index].listIterator();
		}

		public boolean hasNext() {
			if (iterator.hasNext()) {
				return true;
			}
			while (index < priorities) {
				if (index == priorities - 1 || iterator.hasNext()) {
					break;
				}
				iterator = priorityList[++index].listIterator();
			}
			return iterator.hasNext();
		}

		public boolean hasPrevious() {
			if (iterator.hasPrevious()) {
				return true;
			}
			while (index >= 0) {
				if (index == 0 || iterator.hasPrevious()) {
					break;
				}
				iterator = priorityList[--index].listIterator();
			}
			return iterator.hasPrevious();
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return iterator.next();
		}

		public int nextIndex() {
		  throw new UnsupportedOperationException();
		}

		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			return iterator.previous();
		}

		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		public void add(T object) {
			iterator.add(object);
		}

		public void remove() {
			iterator.remove();
			--size;
		}

		public void set(T object) {
			throw new UnsupportedOperationException();
		}
	}

}
