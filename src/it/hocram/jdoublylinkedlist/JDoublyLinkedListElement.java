/**
 * 
 */
package it.hocram.jdoublylinkedlist;

/**
 * @author hocram
 *
 */
public class JDoublyLinkedListElement<T> implements IJDoublyLinkedListElement<T> {


	private T value;
	private IJDoublyLinkedListElement<T> previous;
	private IJDoublyLinkedListElement<T> next;
	
	/**
	 * 
	 * @param value
	 */
	public JDoublyLinkedListElement(T value) {
		this.setValue(value);
		this.setPrevious(null);
		this.setNext(null);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || this.getValue() == null) {
			return false;
		}
		if ((this == obj) || (this.getValue().equals(obj) || obj.equals(this.getValue()))) {
			return true;
		}
		return false;
	}
	
	public T getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		String json = "{"
				+ "'value':'" + this.getValue().toString() + "'"
				+ "}";
		
		return this.getValue().toString();
	}
	
	public void setValue(T value) {
		this.value = value;
	}

	public IJDoublyLinkedListElement<T> getPrevious() {
		return this.previous;
	}

	public void setPrevious(IJDoublyLinkedListElement<T> previous) {
		this.previous = previous;
	}

	public IJDoublyLinkedListElement<T> getNext() {
		return this.next;
	}

	public void setNext(IJDoublyLinkedListElement<T> next) {
		this.next = next;
		
	}

	
}
