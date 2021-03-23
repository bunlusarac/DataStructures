package datastructures;

/**
 * Node containing a single next link which is used to 
 * implement linked data structures.
 * @author Baris Unlusarac
 *
 * @param <T> type of data stored in the node
 */
public class Node<T>{
	public T data;
	public Node<T> next;

	/**
	 * Initialize a node with given data and 
	 * assign {@code next} reference to {@code null}.
	 * @param data data to be stored in node
	 */
	public Node(T data){
		this(data, null);
	}

	/**
	 * Initialize a node with given data and next node's reference
	 * @param data data to be stored in node
	 * @param next reference to next node
	 */
	public Node(T data, Node<T> next){
		this.data = data;
		this.next = next;
	}
}