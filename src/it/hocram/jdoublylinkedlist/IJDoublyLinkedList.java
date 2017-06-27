/**
 * 
 */
package it.hocram.jdoublylinkedlist;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author hocram
 *
 */
public interface IJDoublyLinkedList<T> extends Iterable<T>, Cloneable, Serializable  {

	///
	
	public boolean isEmpty();

	///
	
	public int size();

	///
	
	public T getFirst();
	public T getLast();
	public T get(int index);

	///
	
	public void add(T value);
	public void addFirst(T value);
	public void addLast(T value);
	public int add(T value, int index) throws Exception;

	///
	
	public T remove(int index);
	public boolean remove(T value);

	///
	
	public int move(int indexOld, int indexNew) throws Exception;
	public int moveToFirst(int index) throws Exception;
	public int moveToLast(int index) throws Exception;

	///
	
	public int search(T value);

	///
	
	public Iterator<T> iterator();

	///////////////////////////////////////////////////////////////////////////

	public IJDoublyLinkedListElement<T> getFirstElement();
	public IJDoublyLinkedListElement<T> getLastElement();
	
	public IJDoublyLinkedListElement<T> getElement(int index);
	public IJDoublyLinkedListElement<T> getElement(T value);
	
	public IJDoublyLinkedListElement<T> removeElement(int index);
	public IJDoublyLinkedListElement<T> removeElement(T value);

	///////////////////////////////////////////////////////////////////////////

}
