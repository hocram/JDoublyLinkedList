/**
 * 
 */
package it.hocram.jdoublylinkedlist;


/**
 * @author hocram
 *
 */
public interface IJDoublyLinkedListElement<T> {

	public T getValue();
	public void setValue(T value);
	
	public IJDoublyLinkedListElement <T> getPrevious();
	public void setPrevious(IJDoublyLinkedListElement <T> previous);
	
	public IJDoublyLinkedListElement <T> getNext();
	public void setNext(IJDoublyLinkedListElement <T>  next);
	
}
