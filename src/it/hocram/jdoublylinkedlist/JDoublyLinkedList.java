/**
 * 
 */
package it.hocram.jdoublylinkedlist;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * @author hocram
 *
 */
public class JDoublyLinkedList<T> implements IJDoublyLinkedList<T> {


	/**
	 * 
	 */
	private IJDoublyLinkedListElement<T> first;

	/**
	 * 
	 */
	private IJDoublyLinkedListElement<T> last;

	/**
	 * 
	 */
	private int size;

	////////////// COSTRUCTOR ///////////////////////////////////////////

	/**
	 * Costructor
	 */
	public JDoublyLinkedList() {
		super();
		this.setFirstElement(null);
		this.setLastElement(null);
	}

	////////////// CLONE ///////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	protected IJDoublyLinkedList<T> clone() throws CloneNotSupportedException {
		super.clone();
		IJDoublyLinkedList<T> list = new JDoublyLinkedList<T>();
		/*
		 * Iterator<T> iterator = this.iterator(); while (iterator.hasNext()) {
		 * list.add(iterator.next()); }
		 */
		for (T value : list) {
			T obj = null;
			if (value instanceof Cloneable) {
				try {
					Method method = Object.class.getDeclaredMethod("clone");
					if (method != null) {
						method.setAccessible(true);
						obj = (T) method.invoke(value);
					}
				} catch (Throwable t) {
					t.printStackTrace();
				}
			} else {
				obj = value;
			}
			if (obj != null) {
				list.add(obj);
			}
		}
		return list;
	}

	////////////// EMPTY ///////////////////////////////////////////

	public boolean isEmpty() {
		return (this.getFirst() == null);
	}

	////////////// SIZE ///////////////////////////////////////////

	public int size() {
		return this.countSize();
	}

	private int countSize() {
		if (isEmpty()) {
			return 0;
		}
		int size;
		Iterator<T> iterator = this.iterator();
		for (size = 0; iterator.hasNext(); iterator.next()) {
			size++;
		}
		return size;
	}

	/// GET T /////////////////////////////////////////////////////////

	public T getFirst() {
		if (this.getFirstElement() == null) {
			return null;
		}
		return this.getFirstElement().getValue();
	}

	public T getLast() {
		return this.getLastElement().getValue();
	}

	public T get(int index) {
		IJDoublyLinkedListElement<T> element = this.getElement(index);
		return (element != null) ? element.getValue() : null;
	}

	/// GET ELEMENT //////////////////////////////////////////////////

	public IJDoublyLinkedListElement<T> getFirstElement() {
		return this.first;
	}

	public IJDoublyLinkedListElement<T> getLastElement() {
		return this.last;
	}

	public IJDoublyLinkedListElement<T> getElement(int index) {
		if (index <= 0) {
			return null;
		}
		IJDoublyLinkedListElement<T> element = this.getFirstElement();
		int position = 1;
		while (element != null && position <= index) {
			if (position == index) {
				return element;
			}
			element = element.getNext();
			position++;
		}
		return null;
	}

	public IJDoublyLinkedListElement<T> getElement(T value) {
		if (value != null) {
			IJDoublyLinkedListElement<T> element = this.getFirstElement();
			while (element != null) {
				if (element.equals(value)) {
					return element;
				}
				element = element.getNext();
			}
		}
		return null;
	}

	/// SET FIRST & LAST //////////////////////////////////////

	private void setFirstElement(IJDoublyLinkedListElement<T> first) {
		this.first = first;
	}

	private void setLastElement(IJDoublyLinkedListElement<T> last) {
		this.last = last;
	}

	/// ADD //////////////////////////////////////////////////

	public void addFirst(T value) {
		IJDoublyLinkedListElement<T> element = new JDoublyLinkedListElement<T>(value);
		if (this.isEmpty()) {
			this.setFirstElement(element);
			this.setLastElement(element);
		} else {
			element.setNext(this.getFirstElement());
			this.getFirstElement().setPrevious(element);
			this.setFirstElement(element);
		}
	}

	public void addLast(T value) {
		IJDoublyLinkedListElement<T> element = new JDoublyLinkedListElement<T>(value);
		if (this.isEmpty()) {
			this.setFirstElement(element);
			this.setLastElement(element);
		} else {
			this.getLastElement().setNext(element);
			element.setPrevious(this.getLastElement());
			this.setLastElement(element);
		}

	}

	public void add(T value) {
		this.addLast(value);
	}

	public int add(T value, int index) throws Exception {
		if (index <= 0) {
			throw new Exception();
		}
		int position = 1;
		if (this.isEmpty()) {
			this.add(value);
		} else {
			IJDoublyLinkedListElement<T> element = new JDoublyLinkedListElement<T>(value);
			IJDoublyLinkedListElement<T> currentNode = this.getFirstElement();
			while (position < index) {
				position++;
				if (currentNode.getNext() == null) {
					this.addLast(value);
					return position;
				}
				currentNode = currentNode.getNext();
			}
			IJDoublyLinkedListElement<T> previous = currentNode.getPrevious();
			element.setNext(currentNode);
			element.setPrevious(previous);
			currentNode.setPrevious(element);
			if (previous != null) {
				previous.setNext(element);
			}
			if (currentNode == this.getFirstElement()) {
				this.setFirstElement(element);
			}
		}
		return position;
	}

	/// REMOVE ////////////////////////////////////////////

	public T remove(int index) {
		IJDoublyLinkedListElement<T> element = removeElement(index);
		return ((element != null) ? element.getValue() : null);
	}

	public boolean remove(T value) {
		return (this.removeElement(value) != null);
	}

	public IJDoublyLinkedListElement<T> removeElement(T value) {
		IJDoublyLinkedListElement<T> element = this.getElement(value);
		element = this.removeElementByList(element);
		return element;
	}

	public IJDoublyLinkedListElement<T> removeElement(int index) {
		if (index <= 0) {
			return null;
		}
		IJDoublyLinkedListElement<T> element = this.getElement(index);
		element = this.removeElementByList(element);
		return element;
	}

	private IJDoublyLinkedListElement<T> removeElementByList(IJDoublyLinkedListElement<T> element) {
		if (element == null) {
			return null;
		}
		if (element == this.getFirstElement()) {
			this.setFirstElement(element.getNext());
		}
		if (element == this.getLastElement()) {
			this.setLastElement(element.getPrevious());
		}
		if (element.getPrevious() != null) {
			element.getPrevious().setNext(element.getNext());
		}
		if (element.getNext() != null) {
			element.getNext().setPrevious(element.getPrevious());
		}
		element.setNext(null);
		element.setPrevious(null);
		return element;
	}

	/// MOVE ////////////////////////////////////////////

	public int move(int indexOld, int indexNew) throws Exception {
		if ((indexOld <= 0) || (indexNew <= 0)) {
			throw new Exception();
		} // TODO mettere caso di errore
		IJDoublyLinkedListElement<T> element = this.removeElement(indexOld);
		if (element == null) {
			return -1;
		}
		return this.add(element.getValue(), indexNew);
	}

	public int moveToFirst(int index) throws Exception {
		return this.move(index, 1);
	}

	public int moveToLast(int index) throws Exception {
		return this.move(index, this.size());
	}

	/// SEARCH ////////////////////////////////////////////

	public boolean contains(T value) {
		return ( search(value) != -1 );
	}

	public int search(T value) {
		if (value == null) {
			return -1;
		}
		Iterator<T> iterator = this.iterator();
		for (int position = 1; iterator.hasNext(); position++) {
			T node = iterator.next();
			if (node.equals(value) || value.equals(node)) {
				return position;
			}
		}
		return -1;
	}

	/// ITERATOR ////////////////////////////////////////////

	public Iterator<T> iterator() {
		return this.externalIterator();
	}

	private Iterator<T> externalIterator() {
		return new Iterator<T>() {
			private Iterator<IJDoublyLinkedListElement<T>> iterator = JDoublyLinkedList.this.internalIterator();

			public boolean hasNext() {
				return this.iterator.hasNext();
			}

			public T next() {
				IJDoublyLinkedListElement<T> current = this.iterator.next();
				if (current == null) {
					return null;
				}
				return (T) current.getValue();
			}

			public void remove() {
				// TODO Auto-generated method stub

			}

		};
	}

	@SuppressWarnings("rawtypes")
	private Iterator<IJDoublyLinkedListElement<T>> internalIterator() {
		return new Iterator<IJDoublyLinkedListElement<T>>() {
			private IJDoublyLinkedListElement<T> current = JDoublyLinkedList.this.getFirstElement();
			private boolean isFirst = false;

			public boolean hasNext() {
				if ((this.isFirst == false) && (this.current != null)) {
					return true;
				}
				return ((this.current != null) && (this.current.getNext() != null));
			}

			public IJDoublyLinkedListElement<T> next() {
				if ((this.isFirst == false) && (this.current != null)) {
					this.isFirst = true;
					return this.current;
				}
				//
				if (!this.hasNext()) {
					return null; // throw new NoSuchElementException();
				}
				this.current = this.current.getNext();
				return this.current;
			}

			public void remove() {
				// TODO Auto-generated method stub
			}
		};
	}

}
