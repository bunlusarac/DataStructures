package datastructures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

import datastructures.LinkedIterator;
import datastructures.Node;

/**
 * Singly-linked list implementation using linked 
 * {@code Node<T>} objects to store elements.
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the list
 */
public class LinkedList<T> implements Iterable<T>{
    private Node<T> head; 
    private int size;
    
    public LinkedList(){
    	this.head = null;
    	this.size = 0;
    }

	/**
	 * Returns a string representation of the list, in which elements
	 * are spaced with an empty whitespace character.
	 * @return string representation of the list
	 */
    @Override
    public String toString(){
        StringBuilder listString = new StringBuilder();
        Node<T> cursor = head;

        while(cursor != null){
            listString.append(cursor.data);
            listString.append(' ');
            cursor = cursor.next;
        }

        return listString.toString();
    }

	/**
	 * Returns a boolean value indicating emptiness of the list.
	 * @return true if list is empty, false otherwise
	 */
    public boolean isEmpty(){ return head == null; }
    
	/**
	 * Returns size of the list. (number of elements in the list)
	 * @return size of the list/number of elements
	 */
    public int size(){ return size; }
    
    /**
     * Add an element to the beginning of the list.
     * <p>Time complexity of this operation is O(1).</p>
     * @param data data of the new element
     * @return Reference to the new element node
     */
    public Node<T> addFirst(T data){
        Node<T> newNode = new Node<T>(data, head);
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
    public Node<T> addLast(T data){ 
    	if(isEmpty()) {
    		return addFirst(data);
        }
        else {
	    	Node<T> cursor = head;
	        
	        while(cursor.next != null)
	            cursor = cursor.next;
	
	        Node<T> newNode = new Node<T>(data);    
	        cursor.next = newNode;
	        ++size;
	        
	        return newNode;
        }
    }
    
    /**
     * Adds an element to given index.
     * <p>Time complexity of this operation is O(n) in worst case, O(1) in best case.</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * @param index index of the new element
     * @param data data of the new element
     * @return Reference to the new element node
     */
    public Node<T> add(int index, T data){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if(index == 0){
            return addFirst(data);
        }
        else{
            Node<T> cursor = head;

            for(int i=0; i < index-1; ++i)
                cursor = cursor.next;

            Node<T> newNode = new Node<T>(data, cursor.next);
            cursor.next = newNode;
            
            ++size;
            return newNode;
        }
    }
    
    /**
     * Adds an element after given node
     * <p>Time complexity of this operation is O(1).</p>
     * @param node reference to node that new element is going
     * to be inserted after
     * @param data data of the new element
     * @return Reference to the new element node
     */
    public Node<T> addAfter(Node<T> node, T data) {
    	Node<T> newNode = new Node<>(data, node.next);
		node.next = newNode;
		
		return newNode;
    }
    
    /**
     * Deletes an element after given node
     * <p>Time complexity of this operation is O(1).</p>
     * <p>Throws {@code NoSuchElementException} if there is no element 
     * after given node.</p>
     * @param node reference to node just before the
     * element that is going to be deleted.
     */
    public void removeAfter(Node<T> node) {
    	if(node.next == null)
    		throw new NoSuchElementException("No element after given node");
    	else
    		node.next = node.next.next;
    }
    
    /**
     * Removes first element from the list.
     * <p>Time complexity of this operation is O(1).</p>
     * <p>Throws {@code NoSuchElementException} if list is empty.</p>
     */
    public void removeFirst(){
        emptyCheck();
        
        head = head.next;
        --size;
    }

    /**
     * Removes last element from the list.
     * <p>Time complexity of this operation is O(n).</p>
     * <p>Throws {@code NoSuchElementException} if list is empty.</p>
     */
    public void removeLast(){
        emptyCheck();
            
        Node<T> cursor = head;
        if(cursor.next == null) {
        	head = null;
        }
        else {
	        while(cursor.next.next != null)
	            cursor = cursor.next;
	
	        cursor.next = null;
        }
        
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
            Node<T> cursor = head;

            for(int i=0; i < index-1; ++i)
                cursor = cursor.next;

            cursor.next = cursor.next.next;
        }
        
        --size;
    }

    /**
     * Return index of first occurrence of given element in the list. 
     * <p>Time complexity of this operation is O(n) in worst case, O(1) in best case.</p>
     * <p>Throws {@code NoSuchElementException} if given element is not found.</p>
     * @param data data of the element whose index is being looked for
     */
    public int search(T data){
        Node<T> cursor = head;

        for(int index=0; cursor != null; ++index){
            if(cursor.data == data)
                return index;

            cursor = cursor.next;
        }

        throw new NoSuchElementException("Element not found.");
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
    	
        Node<T> cursor = head;
        
        for(int i=0; i < index; ++i)
        	cursor = cursor.next;
        
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
        
        Node<T> cursor = head;
        
        for(int i=0; i < index; ++i)
        	cursor = cursor.next;
        
        cursor.data = data;
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
        size = 0;
    }

    public Iterator<T> iterator(){
        return new LinkedIterator<T>(head);
    }
    
    private void emptyCheck() {
        if(isEmpty())
            throw new NoSuchElementException("List is empty");
    }
}