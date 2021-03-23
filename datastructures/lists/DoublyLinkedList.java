
package datastructures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

import datastructures.DoubleNode;
import datastructures.DoublyLinkedIterator;

/**
 * Doubly-linked list implementation using {@code DoubleNode<T>} objects
 * to store elements.
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the list
 */
public class DoublyLinkedList<T> implements Iterable<T>{
	private DoubleNode<T> head;
	private DoubleNode<T> tail;
	private int size;
	
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/**
	 * Returns size of the list. (number of elements in the list)
	 * @return size of the list/number of elements
	 */
	public int size(){ return size; }
	
	/**
	 * Returns a boolean value indicating emptiness of the list.
	 * @return true if list is empty, false otherwise
	 */
	public boolean isEmpty() { return head == null; }
	
	/**
	 * Returns a string representation of the list, in which elements
	 * are spaced with an empty whitespace character.
	 * @return string representation of the list
	 */
    @Override
    public String toString(){
        StringBuilder listString = new StringBuilder();
        DoubleNode<T> cursor = head;

        while(cursor != null){
            listString.append(cursor.data);
            listString.append(' ');
            cursor = cursor.next;
        }

        return listString.toString();
    }
	
    /**
     * Adds an element to the beginning of the list.
     * <p>Time complexity of this operation is O(1).</p>
     * @param data data of the new element
     * @return Reference to the new element node.
     */
	public DoubleNode<T> addFirst(T data) {
		DoubleNode<T> newNode = new DoubleNode<T>(data, head);
		
		if(isEmpty()) 
			tail = newNode;
		else
			head.prev = newNode;
		
		head = newNode;
		
		++size;
		return newNode;
	}
	
    /**
     * Add an element to the end of the list.
     * <p>Time complexity of this operation is O(n).</p>
     * @param data data of the new element
     * @return Reference to the new element node
     */
	public DoubleNode<T> addLast(T data) {
		DoubleNode<T> newNode = new DoubleNode<T>(data, null, tail);
		
		if(isEmpty())
			head = newNode;
		else 
			tail.next = newNode;
			
		tail = newNode;	
		
		++size;
		return newNode;
	}

    /**
     * Adds an element after given node
     * <p>Time complexity of this operation is O(1).</p>
     * @param node reference to node that new element is going
     * to be inserted after
     * @param data data of the new element
     * @return Reference to the new element node
     */
	public DoubleNode<T> addAfter(DoubleNode<T> node, T data) {
		DoubleNode<T> newNode = new DoubleNode<T>(data, node.next, node);
		
		if(node != tail)
			node.next.prev = newNode;
		else
			tail = newNode;
		
		node.next = newNode;
		
		++size;
		return newNode;
	}
	
    /**
     * Adds an element before given node
     * <p>Time complexity of this operation is O(1).</p>
     * @param node reference to node where new element is going
     * to be inserted before
     * @param data data of the new element
     * @return Reference to the new element node
     */
	public DoubleNode<T> addBefore(DoubleNode<T> node, T data) {
		DoubleNode<T> newNode = new DoubleNode<T>(data, node, node.prev);
		
		if(node != head)
			node.prev.next = newNode;
		else
			head = newNode;
		
		node.prev = newNode;
		
		++size;
		return newNode;
	}
	
	/**
	 * Adds an element which will have the index {@code index}.
	 * <p>Time complexity of this operation is O(n) in worst 
	 * case, O(1) in best case.</p>
	 * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
	 * @param index the new element's index
	 * @param data data of the new element
	 * @return Reference to the new element node
	 */
	public DoubleNode<T> add(int index, T data) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if(index == 0){
            return addFirst(data);
        }
        else{
            DoubleNode<T> cursor = head;

            for(int i=0; i < index-1; ++i)
                cursor = cursor.next;

            DoubleNode<T> newNode = new DoubleNode<T>(data, cursor.next);
            cursor.next = newNode;
            
            ++size;
            return newNode;
        }
	}
	
    /**
     * Delete all elements and clear the list.
     * <p>This does not free memory allocated by nodes immediately but 
     * removes the reference to the list, which will make nodes viable
     * for garbage collection so that their memory will eventually get
     * freed.</p>
     */
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Removes first element from the list.
     * <p>Time complexity of this operation is O(1).</p>
     * <p>Throws {@code NoSuchElementException} if list is empty.</p>
     */
    public void removeFirst() {
    	if(isEmpty())
    		throw new NoSuchElementException("List is empty");
    	
    	head = head.next;
    	
    	if(head != null)
    		head.prev = null;
    	
    	--size;
    }
    
    /**
     * Removes last element from the list.
     * <p>Time complexity of this operation is O(n).</p>
     * <p>Throws {@code NoSuchElementException} if list is empty.</p>
     */
    public void removeLast() {
    	if(isEmpty())
    		throw new NoSuchElementException("List is empty");
    	
    	tail = tail.prev;
    	
    	if(tail != null)
    		tail.next = null;
    	
    	--size;
    }
    
    /**
     * Delete an element of given index.
     * <p>Time complexity of this operation is O(n) in worst case, O(1) in best case.</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * @param index index of the element to be deleted.
     */
    public void remove(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        if(index == 0){
            head = head.next;
        }
        else{
            DoubleNode<T> cursor = head;

            for(int i=0; i < index-1; ++i)
                cursor = cursor.next;

            cursor.next = cursor.next.next;
        }
        
        --size;
    }
    
    /**
     * Delete an element of given index.
     * <p>Time complexity of this operation is O(n) in worst case, O(1) in best case.</p>
     * <p>Throws {@code NoSuchElementException} if list is empty.</p>
     * @param index index of the element to be deleted.
     */
    public void remove(DoubleNode<T> node) {
    	if(node == head)
    		removeFirst();
    	else if(node == tail)
    		removeLast();
    	else {
	    	node.prev.next = node.next;
	    	node.next.prev = node.prev;
	    	--size;
    	}
    }

    /**
     * Get data of element at given index
     * <p>Time complexity of this operation is O(n) in worst case, O(1) in best case.</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * @param index index to get data from
     * @return data at given index
     */
    public T get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    	
        DoubleNode<T> cursor;
        
        if(index <= size/2) {
        	cursor = head;
        	
	        for(int i=0; i < index; ++i)
	        	cursor = cursor.next;
        }
        else {
        	cursor = tail;
        	
	        for(int i=0; i < (size - 1) - index; ++i)
	        	cursor = cursor.prev;
        }
        
        return cursor.data;
    }

    /**
     * Set data of element at given index
     * <p>Time complexity of this operation is O(n) in worst case, O(1) in best case.</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * @param index index of element whose data will be changed
     * @param data data to be assigned to the element
     */
    public void set(int index, T data) {
    	if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    	
        DoubleNode<T> cursor;
        
        if(index <= size/2) {
        	cursor = head;
        	
	        for(int i=0; i < index; ++i)
	        	cursor = cursor.next;
        }
        else {
        	cursor = tail;
        	
	        for(int i=0; i < (size - 1) - index; ++i)
	        	cursor = cursor.prev;
        }
        
        cursor.data = data;
    }
    
    public Iterator<T> iterator(){
        return new DoublyLinkedIterator<T>(head);
    }
}
