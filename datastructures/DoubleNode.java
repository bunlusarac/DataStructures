package datastructures;

/**
 * Node containing a two links ({@code next} and {@code prev}) 
 * which is used to implement doubly linked list.
 * @author Baris Unlusarac
 *
 * @param <T> type of data stored in the node
 */
public class DoubleNode<T>{
    public T data;
    public DoubleNode<T> next;
    public DoubleNode<T> prev;
    
	/**
	 * Initialize a node with given data and 
	 * assign {@code next} and {@code prev} to {@code null}.
	 * @param data data to be stored in node
	 */
    public DoubleNode(T data) {
        this(data, null, null);
    }

	/**
	 * Initialize a node with given data and next node's reference,
	 * assign {@code prev} to {@code null}.
	 * @param data data to be stored in node
	 * @param next reference to next node
	 */
    public DoubleNode(T data, DoubleNode<T> next) {
        this(data, next, null);
    }
    
	/**
	 * Initialize a node with given data, next and 
	 * previous node's reference.
	 * @param data data to be stored in node
	 * @param next reference to next node
	 * @param prev reference to previous node
	 */
    public DoubleNode(T data, DoubleNode<T> next, DoubleNode<T> prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}